package edu.hfut.back_end.Utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTP;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author HeRunLin
 */
@Slf4j
public class FTPUtils {

    private static final FTPClient FTP_CLIENT = new FTPClient();

    private static void testConnect() throws IOException {
        FTP_CLIENT.connect("121.40.130.17", 21);
        FTP_CLIENT.login("ftpuser", "ftp654123");
    }

    public static void uploadFile(String remoteDir, String targetFileName, InputStream fileInputStream) throws IOException {
//        System.out.println("开始上传文件");
        testConnect();
        FTP_CLIENT.changeWorkingDirectory(remoteDir);
        FTP_CLIENT.setFileType(FTP.BINARY_FILE_TYPE);
        FTP_CLIENT.storeFile(targetFileName, fileInputStream);
//        System.out.println("文件上传成功");
        FTP_CLIENT.logout();
    }
}
