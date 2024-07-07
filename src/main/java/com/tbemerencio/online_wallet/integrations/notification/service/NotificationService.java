package com.tbemerencio.online_wallet.integrations.notification.service;

import com.tbemerencio.online_wallet.entities.Transfer;
import com.tbemerencio.online_wallet.integrations.notification.client.NotificationClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    private final NotificationClient notificationClient;

    public NotificationService(NotificationClient notificationClient){
        this.notificationClient = notificationClient;
    }

    public void sendNotification(Transfer transfer){
        try {
            logger.info("Sending notification...");
            var response = notificationClient.sendNotification(transfer).getStatusCode();
            if (response.isError()){
                logger.error("Error while sending notification, status code not OK");
            }
        } catch (Exception e){
            logger.error("Error while sending notification", e);
        }
    }
}