package com.notionoutlooksync;

import com.notionoutlooksync.api.NotionClient;
import com.notionoutlooksync.model.NotionPage;

public class Main {
    public static void main(String[] args) {
        System.out.println("Notion-Outlook Calender Sync");
        NotionClient notionClient = new NotionClient();
        NotionPage page = notionClient.getNotionData();
        if(page != null){
            System.out.println("Page ID: "+page.getId());
        }
    }
}
