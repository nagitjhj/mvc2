package com.hi.mvc2basic.fileupload.controller;

import com.hi.mvc2basic.fileupload.domain.FileItem;
import com.hi.mvc2basic.fileupload.domain.FileItemRepository;
import com.hi.mvc2basic.fileupload.domain.UploadFile;
import com.hi.mvc2basic.fileupload.file.FileStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriUtils;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/file")
@RequiredArgsConstructor
public class FileItemController {
    private final FileItemRepository repository;
    private final FileStore fileStore;

    @GetMapping("/items/new")
    public String newItem(@ModelAttribute ItemForm form) {
        return "file/item-form";
    }

    @PostMapping("/items/new")
    public String saveItem(@ModelAttribute ItemForm form, RedirectAttributes redirectAttributes) throws IOException {
        UploadFile attachFile = fileStore.storeFile(form.getAttachFile()); //단일 첨부파일 처리
        List<UploadFile> storeImageFiles = fileStore.storeFiles(form.getImageFiles()); //이미지 파일들 처리

        FileItem fileItem = new FileItem();
        fileItem.setItemName(form.getItemName());
        fileItem.setAttachFile(attachFile);
        fileItem.setImageFiles(storeImageFiles);
        repository.save(fileItem);

        redirectAttributes.addAttribute("itemId", fileItem.getId());

        return "redirect:/file/items/{itemId}";
    }

    @GetMapping("/items/{id}")
    public String items(@PathVariable Long id, Model model) {
        FileItem fileItem = repository.findById(id);
        model.addAttribute("item", fileItem);
        return "file/item-view";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }

    @GetMapping("/attach/{itemId}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Long itemId) throws MalformedURLException {
        FileItem fileItem = repository.findById(itemId);
        String storeFileName = fileItem.getAttachFile().getStoreFileName();
        String uploadFileName = fileItem.getAttachFile().getUploadFileName();

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(storeFileName));

        log.info("uploadFileName={}", uploadFileName);
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }

    @GetMapping("/attach/{itemId}/{fileName}")
    public ResponseEntity<Resource> downloadAttach(@PathVariable Map<String, String> map) throws MalformedURLException {
        FileItem fileItem = repository.findById(Long.valueOf(map.get("itemId")));
        String uploadFileName = null;

        for (UploadFile uploadFile : fileItem.getImageFiles()) {
            if (uploadFile.getStoreFileName().equals(map.get("fileName"))) {
                uploadFileName = uploadFile.getUploadFileName();
                break;
            }
        }

        UrlResource resource = new UrlResource("file:" + fileStore.getFullPath(map.get("fileName")));

        log.info("uploadFileName={}", uploadFileName);
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, contentDisposition)
                .body(resource);
    }
}
