package com.ayd2.imporcomgua.services.mail;

import com.ayd2.imporcomgua.models.warehouse.Inventory;

public interface MailService {
    boolean sendInventoryNotification(Inventory inventory);
}
