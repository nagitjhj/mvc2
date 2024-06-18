package com.hi.mvc2basic.fileupload.domain;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class FileItemRepository {
    private final Map<Long, FileItem> store = new HashMap<>();
    private long sequence = 0L;

    public FileItem save(FileItem fileItem) {
        fileItem.setId(++sequence);
        store.put(fileItem.getId(), fileItem);
        return fileItem;
    }

    public FileItem findById(Long id) {
        return store.get(id);
    }
}
