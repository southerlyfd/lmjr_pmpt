package com.pmpt.common;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;

@Component
public class QiNiuUploadFileUtil {

    @Value("${qiuniu.access_key}")
    private String ACCESS_KEY;
    @Value("${qiuniu.secret_key}")
    private String SECRET_KEY;
    @Value("${bucket}")
    private String BUCKET;
    @Value("${domain_of_bucket}")
    private String DOMAIN_OF_BUCKET;

    public String uploadFile(InputStream inputStream) throws IOException {
        String encodedFileName;
        try {
            UploadManager uploadManager = new UploadManager(new Configuration(Zone.zone2()));
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            Response uploadResponse = uploadManager.put(inputStream, null, auth.uploadToken(BUCKET), null, null);
            DefaultPutRet putRet = GsonUtil.jsonToObj(uploadResponse.bodyString(), DefaultPutRet.class);
            encodedFileName = URLEncoder.encode(putRet.hash, "utf-8");
        } finally {
            inputStream.close();
        }
        return String.format("%s/%s", DOMAIN_OF_BUCKET, encodedFileName);
    }

    public void deleteUploadFile(String... fileURI) throws QiniuException {
        BucketManager bucketManager = new BucketManager(Auth.create(ACCESS_KEY, SECRET_KEY), new Configuration(Zone.zone2()));
        for (String key : fileURI) {
            key = key.substring(key.lastIndexOf("/") + 1);
            bucketManager.delete(BUCKET, key);
        }

    }

}
