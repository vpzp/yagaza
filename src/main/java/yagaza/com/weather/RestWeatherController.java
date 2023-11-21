package yagaza.com.weather;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*공공데이터포털의 날씨 데이터 파일
xml파일로 되어있고 json파일로 변경 가능
10일간의 강우량, 기온을 알 수 있음
TODO xml파일에서 필요한 데이터만 엔티티에 저장하기
**/
@RestController
public class RestWeatherController {

    @GetMapping("/weatherapi")
    public String callApi() {
        StringBuffer result = new StringBuffer();
        try {
            String apiUrl = "http://apis.data.go.kr/1360000/MidFcstInfoService/getMidLandFcst?" +
                    "serviceKey=fzmCfsH9p8b0uEteCyJHVkpuxV7D3NYn2bRNXjoeJNcEIqlzxveUggHdcaiZtPlWajR30Fo9jswNxq%2Fgdwcs2A%3D%3D" +
                    "&numOfRows=10" +
                    "&pageNo=1" +
                    "&dataType=XMP" +
                    "&regId=11B10101" +
                    "&tmFc=202311210600"
                    ;
            URL url = new URL(apiUrl);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));

            String returnLine;
            result.append("<xmp>");
            while((returnLine = bufferedReader.readLine()) != null) {
                result.append(returnLine + "\n");
            }
            urlConnection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result + "</xmp>";
    }
}
