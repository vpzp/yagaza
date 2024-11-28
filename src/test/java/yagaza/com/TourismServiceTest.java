package yagaza.com;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import yagaza.com.Tourism.TourismService;

@SpringBootTest
public class TourismServiceTest {
    @Autowired
    private TourismService tourismService;

    @Test
    public void createTest(){
        tourismService.create("감천문화마을" , " https://a.cdn-hotels.com/gdcs/production15/d863/dea52703-bdd6-4e71-acd8-a6ec41138c4d.jpg " , "부산" ,  "부산광역시 사하구 감천동 203-9", 0, "문화");
        tourismService.create("해동용궁사" , "https://i.namu.wiki/i/2x-R7RROVzBHVTeOioOADMJobZZYST8txK6Bl7kNPRFeDSPCVHWydeXGdaqLty14OYYOmwdbJdYwL0O8A_ENRw.webp",  "부산", "부산광역시 영도구 동삼동 산 1", 0, "문화");
        tourismService.create("송도베이스테이션(송도해상케이블카)" , "https://mblogthumb-phinf.pstatic.net/MjAyMjA2MjVfMiAg/MDAxNjU2MDkwMTU0NDQx.B5i9uOfpszXW2CERNwrNL3beyjLb29BEBt8deMSkb1Qg.u50HQUuxMDW7KoL160k_HmaAtFWOViVCXhqlti9ZI-kg.JPEG.suk4408/1X5A5845.jpg?type=w800",  "부산", "부산광역시 서구 아미동 산 17-1", 16000, "자연");
        tourismService.create("흰여울문화마을" , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHCiw0J-m7SChqL8Tv3JjvYUfDUwfbo5HnZA&usqp=CAU",  "부산", "경상남도 거제시 장목면 흰여울길 141", 0, "문화");
        tourismService.create("부산 엑스 더 스카이" , "https://happymembers.co.kr/upload/master/shop_a43b7b4eb5605b1fb4c02fd04286b51b.jpg",  "부산", "부산광역시 해운대구 해운대해변로 292번길 35", 27000 , "활동");
        tourismService.create("롯데월드 어드벤처 부산" , "https://www.visitbusan.net/uploadImgs/files/cntnts/20220901105446739",  "부산", "부산광역시 해운대구 해운대해변로 63번길 41", 59000, "활동");
        tourismService.create("다이아몬드타워 (부산타워)" , "https://d2ur7st6jjikze.cloudfront.net/offer_descriptive_images/112149/308477_medium_1670229179.jpg?1670229179",  "부산", "부산광역시 중구 우장산로 37", 0, "자연");
        tourismService.create("아홉산숲" , "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=e6e94b81-3122-4093-9edc-e0d61a94a550",  "부산", "부산광역시 금정구 천마로 250", 5000, "자연");
        tourismService.create("용두산공원" , "https://i.namu.wiki/i/eVkj6jR2za31kz5OUI2Yy_Eu6ZrusY4zjNXH-oI1h-d8_iFa1jvuDDSIBjqXjcom4Ndrhr7QuejriUCvVm7b5w.webp",  "부산", "부산광역시 남구 용두산길 58", 0, "활동");
        tourismService.create("씨라이프 부산 아쿠아리움" , "https://www.visitsealife.com/busan/media/hjlpv2d3/microsoftteams-image-104-%E3%85%82.png?anchor=center&mode=crop&format=webp&quality=80&width=600&height=345",  "부산", "부산광역시 해운대구 구남로 187", 31000, "활동");
        tourismService.create("부산시립미술관" , "https://goguides.azureedge.net/media/tgxjptki/2ddbe82f-4a76-4320-81cf-031d5b93cdd1.jpg?anchor=center&mode=crop&width=1600&height=1066&quality=50",  "부산", "부산광역시 중구 남포로 67번길 25", 5000, "문화");
        tourismService.create("국립해양박물관" , "https://www.mmk.or.kr/mobile/_public/images/knmm/s5_3_2_img1_3.jpg",  "부산", "부산광역시 해운대구 우동 1503", 0, "문화");
        tourismService.create("브레이크아웃 이스케이프 방탈출카페" , "https://mblogthumb-phinf.pstatic.net/MjAyMjA2MDRfMjg4/MDAxNjU0Mjk0MjcwODI1.x849Xxgtqg4VgAnBzn0qVusCPSw_6c9rgoeIsGTqOsog.i51uoM8RgiYBTusV41roTuNf8aWyMAq2EZMRL3RoJ2kg.JPEG.xcl_nr/20220603_130921.jpg?type=w800", "부산", "부산광역시 해운대구 센텀2로 25번길 3, 2층", 24000, "활동");
        tourismService.create("오륙도 스카이워크" , "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d0a9427f-56a2-42c9-a31a-dc22e977d682",  "부산", "부산광역시 해운대구 중동 산 116-1", 0, "자연");
        tourismService.create("BIFF광장" , "https://a.cdn-hotels.com/gdcs/production36/d10/45f075e3-5b5a-46cc-8f68-4544c2a9d8b8.jpg?impolicy=fcrop&w=800&h=533&q=medium",  "부산", "부산광역시 중구 비프광장로 58", 0, "문화");
        tourismService.create("국립부산과학관" , "https://i.namu.wiki/i/_U0gtDTHEPO8Ldq8wIrd-vRpy4mFdZEudzO9ganliUnlSEDh3O_CLK0cKI-66nncg7UyZWonKlqCfLf7PitQjw.webp", "부산", "부산광역시 금정구 부산대학로 63번길 2", 3000, "문화");
        tourismService.create("해운대해수욕장" , "https://i.namu.wiki/i/nTqsAEQdDhy7tkCzhtqHix5kNsCYQuPMvrSsKQSepFkTc6_oLaGYjjvT6Ugk5G1lnCfuyhD6tyPE1j9U6RBcw5MT2qQDH5WRcp96WeyV9PVAU5lcbda4lLC71UlC9Yry5pMm314UmMNxWAGCqObsUw.webp", "부산", "부산광역시 해운대구 해운대해변로 264번길 41", 0, "자연");
        tourismService.create("부산현대미술관" , "http://www.busanbiennale2022.org/media/pages/exhibition/places/mocabusan/5a3dc23161-1659087528/busanhyeondaemisulgwan.jpg",  "부산", "부산광역시 사하구 당리로 235번길 37", 12000, "문화");
        tourismService.create("런닝맨 부산점" , "https://www.visitbusan.net/uploadImgs/files/cntnts/20240426152204562_wufrotr",  "부산", "부산광역시 부산진구 부전동 227-2 삼정타워 10층", 16000, "활동");
        tourismService.create("좌동재래시장" , "https://busan.grandculture.net/gallery/busan/picture/s/GC042P21015_s.jpg",  "부산", "부산광역시 동래구 좌동1로 11", 0, "문화");
    }
}
