package com.notionoutlooksync.api;
import com.google.gson.Gson;
import com.notionoutlooksync.model.NotionPage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import io.github.cdimascio.dotenv.Dotenv;
public class NotionClient {
    private final String notionToken;
    private final CloseableHttpClient httpClient;

    private String pageId;

    public NotionClient(){
        Dotenv dotenv = Dotenv.load();
        this.notionToken = dotenv.get("NOTION_TOKEN");
        this.pageId = dotenv.get("NOTION_PAGE_ID");
        this.httpClient = HttpClients.createDefault();
    }

    public NotionPage getNotionData(){
        try {
            HttpGet request =
                    new HttpGet("https://api.notion.com/v1/pages/"+this.pageId+"/");
            request.setHeader("Authorization", "Bearer "+ this.notionToken);
            request.setHeader("Notion-Version", "2022-06-28");

            CloseableHttpResponse response = httpClient.execute(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            Gson gson = new Gson();
            NotionPage page = gson.fromJson(responseBody, NotionPage.class);
            return page;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
