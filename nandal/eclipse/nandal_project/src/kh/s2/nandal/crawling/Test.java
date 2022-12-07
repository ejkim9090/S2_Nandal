package kh.s2.nandal.crawling;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class Test {

    public static void main(String[] args) {
        // 불러올 이미지 URL
        String imageUrl = "https://d1x9f5mf11b8gz.cloudfront.net/class%2F20221205%2Fa5c68947-f579-457e-8bf8-6e350925b860.jpg";

        try {
            getImageUrl(imageUrl);

        } catch (IOException e) {
        	  // 예외처리
            e.printStackTrace();
        }
    }

    private static void getImageUrl(String imageUrl) throws IOException {
        URL url = null;
        InputStream in = null;
        OutputStream out = null;
        int filenum = 1;

        try {
            url = new URL(imageUrl);
            in = url.openStream();

            // 컴퓨터 또는 서버의 저장할 경로(절대패스로 지정해 주세요.)
            out = new FileOutputStream("E:/z_workspace/java/nandal_project/web/images/"+filenum+".jpg");

            while (true) {
                // 루프를 돌면서 이미지데이터를 읽어들이게 됩니다.
                int data = in.read();

                // 데이터값이 -1이면 루프를 종료하고 나오게 됩니다.
                if (data == -1) {
                    break;
                }

                // 읽어들인 이미지 데이터값을 컴퓨터 또는 서버공간에 저장하게 됩니다.
                out.write(data);
                filenum++;
            }

            // 저장이 끝난후 사용한 객체는 클로즈를 해줍니다.
            in.close();
            out.close();

        } catch (Exception e) {
        	  // 예외처리
            e.printStackTrace();
        } finally {
            // 만일 에러가 발생해서 클로즈가 안됐을 가능성이 있기에
            // NULL값을 체크후 클로즈 처리를 합니다.
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}
