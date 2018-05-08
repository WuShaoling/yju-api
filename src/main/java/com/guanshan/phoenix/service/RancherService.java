package com.guanshan.phoenix.service;

import com.guanshan.phoenix.dao.entity.Cloudware;
import com.guanshan.phoenix.error.ApplicationErrorException;

import java.io.IOException;

public interface RancherService {
    void deleteCloudware(String serviceId, String pulsarId) throws IOException, InterruptedException;
    Cloudware createCloudware(int userId, int imageType, String imageNameVersion) throws ApplicationErrorException, InterruptedException;
    void createUserVolume(int userId) throws IOException;
}
