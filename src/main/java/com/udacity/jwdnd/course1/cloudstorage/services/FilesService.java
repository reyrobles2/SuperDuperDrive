package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Files;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FilesService {

    FilesMapper filesMapper;

    // Constructor
    public FilesService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public Files getFile(Integer fileId) {
        return filesMapper.selectFileById(fileId);
    }

    public List<Files> getAllFiles(Integer userId) {
        return filesMapper.selectAllFiles(userId);
    }

    public Integer addFile(MultipartFile multipartFile, Integer userId) throws IOException {
        Files files = new Files();

        files.setUserId(userId);
        files.setFileName(multipartFile.getOriginalFilename());
        files.setContentType(multipartFile.getContentType());
        files.setFileSize(Long.toString(multipartFile.getSize()));
        files.setFileData(multipartFile.getBytes());

        return filesMapper.insertFile(files);
    }

    public void delFile(Integer fileId) {
        filesMapper.deleteFile(fileId);
    }

    public Boolean fileExist(MultipartFile multipartFile) {
        return filesMapper.selectFileByName(multipartFile.getOriginalFilename()) != null;
    }
}