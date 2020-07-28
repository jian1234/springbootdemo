package com.example.springbootdemo.config;

import com.example.springbootdemo.model.ProgressEntity;
import org.apache.commons.fileupload.ProgressListener;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
public class UploadProgressListener implements ProgressListener {

    private HttpSession session;

    public void setSession(HttpSession session) {
        this.session = session;
        ProgressEntity pe = new ProgressEntity();
        System.out.println("upload_percent 0--------------------------------------");
        session.setAttribute("uploadStatus",pe);
    }

    @Override
    public void update(long readBytes, long allBytes, int index) {
        ProgressEntity pe = (ProgressEntity) session.getAttribute("uploadStatus");
        pe.setReadBytes(readBytes);
        pe.setAllBytes(allBytes);
        pe.setCurrentIndex(index);
    }
}
