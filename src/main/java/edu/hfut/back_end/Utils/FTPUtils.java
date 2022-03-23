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

    private FTPClient ftpClient;

    private FTPUtils() {
        ftpClient = new FTPClient();
    }

    private void testConnect() throws IOException {
        ftpClient.connect("121.40.130.17", 21);
        ftpClient.login("ftpuser", "ftp654123");
    }

    private static class FTPUtilsInstance {
        private static final FTPUtils INSTANCE = new FTPUtils();
    }

    public static FTPUtils getInstance() {
        return FTPUtilsInstance.INSTANCE;
    }

    public void uploadFile(String remoteDir, String targetFileName, InputStream fileInputStream) {
        try {
            log.info("开始上传文件");
            testConnect();
            ftpClient.changeWorkingDirectory(remoteDir);
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.storeFile(targetFileName, fileInputStream);
            log.info("文件上传成功");
        } catch (IOException e) {
            log.error("文件上传失败");
            e.printStackTrace();
        } finally {
            try {
                ftpClient.logout();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
