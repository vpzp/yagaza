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
        tourismService.create("감천문화마을" , " https://a.cdn-hotels.com/gdcs/production15/d863/dea52703-bdd6-4e71-acd8-a6ec41138c4d.jpg " , "예술과 낭만으로 산책을 즐길 수 있는 곳", "부산" ,  "부산광역시 사하구 감천동 203-9", 0, "휴식");
        tourismService.create("해동용궁사" , "https://i.namu.wiki/i/2x-R7RROVzBHVTeOioOADMJobZZYST8txK6Bl7kNPRFeDSPCVHWydeXGdaqLty14OYYOmwdbJdYwL0O8A_ENRw.webp", "해동용궁사는 부산의 대표적인 사찰 중 하나로, 아름다운 경치와 역사적인 가치를 갖고 있습니다. 신라 시대에 건립된 이 사찰은 해안가에 위치하여 바다를 바라보며 여유를 즐길 수 있는 곳입니다.", "부산", "부산광역시 영도구 동삼동 산 1", 0, "역사적 장소");
        tourismService.create("송도베이스테이션(송도해상케이블카)" , "https://mblogthumb-phinf.pstatic.net/MjAyMjA2MjVfMiAg/MDAxNjU2MDkwMTU0NDQx.B5i9uOfpszXW2CERNwrNL3beyjLb29BEBt8deMSkb1Qg.u50HQUuxMDW7KoL160k_HmaAtFWOViVCXhqlti9ZI-kg.JPEG.suk4408/1X5A5845.jpg?type=w800", "송도해상케이블카는 부산의 서쪽에 위치한 아름다운 해안가를 따라 운행되는 케이블카입니다. 이곳을 방문하면 송도해상케이블카를 이용하여 아름다운 해안 풍경을 감상할 수 있습니다.", "부산", "부산광역시 서구 아미동 산 17-1", 16000, "경치");
        tourismService.create("흰여울문화마을" , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHCiw0J-m7SChqL8Tv3JjvYUfDUwfbo5HnZA&usqp=CAU", "흰여울문화마을은 고품격 예술과 문화가 어우러진 공간으로, 전통가옥과 현대적인 건축물이 조화롭게 어우러져 있습니다. 예술 전시관, 공연장, 카페 등 다양한 문화시설과 프로그램이 운영되고 있어 관광객들에게 많은 인기를 끌고 있습니다.", "부산", "경상남도 거제시 장목면 흰여울길 141", 0, "문화");
        tourismService.create("부산 엑스 더 스카이" , "https://happymembers.co.kr/upload/master/shop_a43b7b4eb5605b1fb4c02fd04286b51b.jpg", "엑스 더 스카이는 해운대 해변 근처에 솟아 있는 고층 건물로, 부산의 아름다운 해안가와 도심을 한 눈에 내려다 볼 수 있는 전망대와 다양한 엔터테인먼트 시설을 갖추고 있습니다. 부산을 방문하는 관광객들에게 인기있는 명소 중 하나입니다.", "부산", "부산광역시 해운대구 해운대해변로 292번길 35", 27000 , "활동");
        tourismService.create("롯데월드 어드벤처 부산" , "https://www.visitbusan.net/uploadImgs/files/cntnts/20220901105446739", "해운대 해변에 인접해 있어서 해수욕과 놀이공원을 한꺼번에 즐길 수 있는 곳으로, 롯데그룹이 운영하는 대규모 테마파크입니다. 다양한 놀이기구와 어트랙션을 비롯해 쇼핑몰, 음식점, 영화관 등이 있어 가족 단위나 친구들과 함께 즐길 수 있는 장소입니다.", "부산", "부산광역시 해운대구 해운대해변로 63번길 41", 59000, "활동");
        tourismService.create("다이아몬드타워 (부산타워)" , "https://d2ur7st6jjikze.cloudfront.net/offer_descriptive_images/112149/308477_medium_1670229179.jpg?1670229179", "부산타워는 해운대 해수욕장과 광안대교를 한눈에 볼 수 있는 환상적인 전망을 제공합니다. 특히 저녁이면 불빛으로 물들어 아름다운 야경을 감상할 수 있습니다. 부산을 방문하신다면 꼭 한번 방문해보세요!", "부산", "부산광역시 중구 우장산로 37", 0, "경치");
        tourismService.create("아홉산숲" , "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=e6e94b81-3122-4093-9edc-e0d61a94a550", "아홉산숲은 부산 금정구에 위치한 자연휴양림으로, 시원한 산책로와 푸르른 숲속을 즐길 수 있습니다. 특히 가을에는 단풍이 아름다운 풍경을 만들어내며, 여름에는 시원한 그늘 아래에서 산책을 즐길 수 있습니다. 부산의 자연을 만끽하고 싶은 이들에게 추천합니다.", "부산", "부산광역시 금정구 천마로 250", 5000, "휴식");
        tourismService.create("용두산공원" , "https://i.namu.wiki/i/eVkj6jR2za31kz5OUI2Yy_Eu6ZrusY4zjNXH-oI1h-d8_iFa1jvuDDSIBjqXjcom4Ndrhr7QuejriUCvVm7b5w.webp", "용두산공원은 부산 남구에 위치한 공원으로, 부산타워와 함께 부산의 대표적인 랜드마크 중 하나입니다. 공원 내에는 산책로와 휴식 공간뿐만 아니라 전망대도 마련되어 있어 부산시내 전체를 조망할 수 있습니다. 특히 봄에는 벚꽃이 만발하여 아름다운 풍경을 연출하며, 가을에는 단풍이 황금빛으로 물들어 멋진 풍경을 선사합니다. 부산의 아름다운 자연을 감상하고 싶다면 용두산공원을 방문해보세요.", "부산", "부산광역시 남구 용두산길 58", 0, "활동");
        tourismService.create("씨라이프 부산 아쿠아리움" , "https://www.visitsealife.com/busan/media/hjlpv2d3/microsoftteams-image-104-%E3%85%82.png?anchor=center&mode=crop&format=webp&quality=80&width=600&height=345", "씨라이프 부산 아쿠아리움은 해운대 해변 근처에 위치한 대규모 수족관입니다. 다양한 해양 생물을 감상할 수 있는 전시관과 체험 활동, 상점 등이 마련되어 있어 가족이나 친구들과 함께 재미있는 시간을 보낼 수 있습니다. 부산의 다채로운 해양 생태계를 탐험하고 싶다면 씨라이프 부산 아쿠아리움을 방문해보세요.", "부산", "부산광역시 해운대구 구남로 187", 31000, "활동");
        tourismService.create("부산시립미술관" , "https://goguides.azureedge.net/media/tgxjptki/2ddbe82f-4a76-4320-81cf-031d5b93cdd1.jpg?anchor=center&mode=crop&width=1600&height=1066&quality=50", "부산시립미술관은 부산 중구에 위치한 미술관으로, 다양한 전시 및 문화 프로그램을 통해 시민들에게 예술을 즐길 수 있는 기회를 제공하고 있습니다. 국내외 작가들의 작품 전시뿐만 아니라 다양한 예술 이벤트와 교육 프로그램도 운영되어 부산의 예술 문화 발전에 기여하고 있습니다. 부산을 방문하는 이들에게는 부산시립미술관에서 다채로운 예술을 만나보는 것을 추천합니다.", "부산", "부산광역시 중구 남포로 67번길 25", 5000, "문화");
        tourismService.create("국립해양박물관" , "https://www.mmk.or.kr/mobile/_public/images/knmm/s5_3_2_img1_3.jpg", "국립해양박물관은 부산 해운대구에 위치한 해양 박물관으로, 다양한 해양 생태계와 자원에 대한 전시물과 정보를 제공합니다. 해양과 관련된 다양한 주제로 구성된 전시물을 통해 해양 생태계의 아름다움과 중요성을 알아보고, 해양 자원 관리에 대한 인식을 높일 수 있습니다. 또한 박물관은 다양한 교육 프로그램과 체험 활동도 운영하여 방문객들에게 유익한 시간을 제공합니다. 부산을 방문하는 이들에게는 국립해양박물관에서 해양 문화와 자원에 대한 흥미로운 경험을 즐기는 것을 추천합니다.", "부산", "부산광역시 해운대구 우동 1503", 0, "문화");
        tourismService.create("브레이크아웃 이스케이프 방탈출카페" , "https://mblogthumb-phinf.pstatic.net/MjAyMjA2MDRfMjg4/MDAxNjU0Mjk0MjcwODI1.x849Xxgtqg4VgAnBzn0qVusCPSw_6c9rgoeIsGTqOsog.i51uoM8RgiYBTusV41roTuNf8aWyMAq2EZMRL3RoJ2kg.JPEG.xcl_nr/20220603_130921.jpg?type=w800", "브레이크아웃 이스케이프는 해운대구에 위치한 방탈출카페로, 다양한 테마의 탈출 게임을 즐길 수 있는 곳입니다. 방탈출 게임은 팀원들과 협력하여 주어진 시간 내에 미션을 해결하고 탈출하는 것이 목표입니다. 짜릿한 스릴과 퍼즐을 풀어나가는 재미를 느낄 수 있으며, 카페 내부는 테마에 맞게 꾸며져 있어 게임에 몰입할 수 있습니다. 부산에서 친구나 가족과 함께 즐길 수 있는 활동 중 하나로 추천됩니다.", "부산", "부산광역시 해운대구 센텀2로 25번길 3, 2층", 24000, "활동");
        tourismService.create("오륙도 스카이워크" , "https://cdn.visitkorea.or.kr/img/call?cmd=VIEW&id=d0a9427f-56a2-42c9-a31a-dc22e977d682", "오륙도 스카이워크는 부산 해운대구에 위치한 오륙도에 있는 유리 바닥 스카이워크로, 오륙도의 해안 경관을 한눈에 볼 수 있는 명소입니다. 전방과 아래로 유리 바닥이 설치되어 있어 걸어다니면서 아래 해안의 푸른 바다와 바위를 감상할 수 있습니다. 특히 해를 향해 걷는다는 신선한 경험을 느낄 수 있으며, 부산 여행 중에는 꼭 한번 방문해보세요.", "부산", "부산광역시 해운대구 중동 산 116-1", 0, "경치");
        tourismService.create("BIFF광장" , "https://a.cdn-hotels.com/gdcs/production36/d10/45f075e3-5b5a-46cc-8f68-4544c2a9d8b8.jpg?impolicy=fcrop&w=800&h=533&q=medium", "BIFF광장은 부산 국제 영화제가 열리는 장소로 유명한 광장입니다. 부산의 대표적인 상징 중 하나로, 매년 영화제 기간에는 세계 각국의 영화인과 관객들로 북적이는 곳이기도 합니다. 광장 주변에는 영화관, 상점, 레스토랑 등이 밀집해 있어 관광객들에게 다양한 즐길 거리를 제공합니다. 특히 야간에는 환상적인 조명이 켜져 더욱 멋진 분위기를 만들어내어 방문객들을 매료시킵니다. BIFF광장은 부산을 대표하는 관광 명소 중 하나이며, 부산을 여행하는 이들에게 추천하는 곳입니다.", "부산", "부산광역시 중구 비프광장로 58", 0, "문화");
        tourismService.create("국립부산과학관" , "https://i.namu.wiki/i/_U0gtDTHEPO8Ldq8wIrd-vRpy4mFdZEudzO9ganliUnlSEDh3O_CLK0cKI-66nncg7UyZWonKlqCfLf7PitQjw.webp", "국립부산과학관은 부산광역시 금정구에 위치한 과학 및 기술 관련 박물관입니다. 다양한 전시물과 체험 프로그램을 통해 자연과 과학에 대한 흥미를 유발하고, 방문객들에게 즐거운 학습 경험을 제공합니다. 부산과학관은 부산 지역의 교육과 문화를 증진시키는데 기여하며, 과학과 기술 분야에 관심이 있는 이들에게 좋은 장소로 손꼽힙니다. 부산을 방문하는 이들에게는 국립부산과학관을 방문하여 다양한 과학적 지식과 경험을 쌓아보는 것을 추천합니다.", "부산", "부산광역시 금정구 부산대학로 63번길 2", 3000, "문화");
        tourismService.create("해운대해수욕장" , "https://i.namu.wiki/i/nTqsAEQdDhy7tkCzhtqHix5kNsCYQuPMvrSsKQSepFkTc6_oLaGYjjvT6Ugk5G1lnCfuyhD6tyPE1j9U6RBcw5MT2qQDH5WRcp96WeyV9PVAU5lcbda4lLC71UlC9Yry5pMm314UmMNxWAGCqObsUw.webp", "해운대해수욕장은 부산의 대표적인 해변 중 하나로, 깨끗한 백사장과 넓은 해안선이 특징입니다. 맑은 바다와 화사한 해변가에서 여름철에는 많은 사람들이 해수욕을 즐기며 시원한 바다를 누리고, 겨울에는 아름다운 일출을 감상하기 위해 찾는 곳이기도 합니다. 해수욕뿐만 아니라 다양한 해변 행사와 먹거리 등이 가까운 거리에 있어 가족, 커플, 친구들과 함께 즐길 수 있는 장소로 유명합니다. 부산을 방문하는 이들에게는 해운대해수욕장을 꼭 방문해보는 것을 추천합니다.", "부산", "부산광역시 해운대구 해운대해변로 264번길 41", 0, "경치");
        tourismService.create("부산현대미술관" , "http://www.busanbiennale2022.org/media/pages/exhibition/places/mocabusan/5a3dc23161-1659087528/busanhyeondaemisulgwan.jpg", " 부산현대미술관은 현대미술 작품을 전시하는 예술 공간으로, 다양한 전시물과 프로그램을 제공합니다. 국내외의 작가들의 작품뿐만 아니라 부산 지역의 예술가들의 작품도 소개되며, 현대미술에 대한 다양한 시각을 제공합니다. 부산의 미술 문화를 즐기고 싶다면 부산현대미술관을 방문하여 현대미술의 아름다움과 다양성을 경험해보세요.", "부산", "부산광역시 사하구 당리로 235번길 37", 12000, "문화");
        tourismService.create("런닝맨 부산점" , "https://www.visitbusan.net/uploadImgs/files/cntnts/20240426152204562_wufrotr", "SBS 인기 예능프로그램 <런닝맨>을 그대로 옮겨, 직접 런닝맨 멤버가 되어 다양한 미션을 클리어하는 체험형 어트랙션입니다.", "부산", "부산광역시 부산진구 부전동 227-2 삼정타워 10층", 16000, "활동");
        tourismService.create("좌동재래시장" , "https://busan.grandculture.net/gallery/busan/picture/s/GC042P21015_s.jpg", "좌동재래시장은 부산 동래구에 위치한 전통적인 재래시장으로, 다양한 상점들이 모여 있습니다. 신선한 해산물, 식재료, 의류, 생활용품 등을 저렴한 가격에 구매할 수 있으며, 부산 지역의 현지 음식도 즐길 수 있습니다. 부산을 방문하는 이들에게는 부산의 현지 문화와 맛을 경험할 수 있는 좌동재래시장을 방문하는 것을 추천합니다.", "부산", "부산광역시 동래구 좌동1로 11", 0, "문화");
    }
}