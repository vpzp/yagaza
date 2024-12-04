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
    public void createTourism(){
        tourismService.create("감천문화마을" , " https://a.cdn-hotels.com/gdcs/production15/d863/dea52703-bdd6-4e71-acd8-a6ec41138c4d.jpg " , "부산" ,  "부산광역시 사하구 감천동 203-9", 0, "문화");
        tourismService.create("해동용궁사" , "https://i.namu.wiki/i/2x-R7RROVzBHVTeOioOADMJobZZYST8txK6Bl7kNPRFeDSPCVHWydeXGdaqLty14OYYOmwdbJdYwL0O8A_ENRw.webp",  "부산", "부산광역시 영도구 동삼동 산 1", 0, "문화");
        tourismService.create("흰여울문화마을" , "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHCiw0J-m7SChqL8Tv3JjvYUfDUwfbo5HnZA&usqp=CAU",  "부산", "경상남도 거제시 장목면 흰여울길 141", 0, "문화");
        tourismService.create("부산 엑스 더 스카이" , "https://happymembers.co.kr/upload/master/shop_a43b7b4eb5605b1fb4c02fd04286b51b.jpg",  "부산", "부산광역시 해운대구 해운대해변로 292번길 35", 27000 , "활동");
        tourismService.create("롯데월드 어드벤처 부산" , "https://www.visitbusan.net/uploadImgs/files/cntnts/20220901105446739",  "부산", "부산광역시 해운대구 해운대해변로 63번길 41", 59000, "활동");
        tourismService.create("다이아몬드타워 (부산타워)" , "https://d2ur7st6jjikze.cloudfront.net/offer_descriptive_images/112149/308477_medium_1670229179.jpg?1670229179",  "부산", "부산광역시 중구 우장산로 37", 0, "자연");
        tourismService.create("용두산공원" , "https://i.namu.wiki/i/eVkj6jR2za31kz5OUI2Yy_Eu6ZrusY4zjNXH-oI1h-d8_iFa1jvuDDSIBjqXjcom4Ndrhr7QuejriUCvVm7b5w.webp",  "부산", "부산광역시 남구 용두산길 58", 0, "활동");
        tourismService.create("씨라이프 부산 아쿠아리움" , "https://www.visitsealife.com/busan/media/hjlpv2d3/microsoftteams-image-104-%E3%85%82.png?anchor=center&mode=crop&format=webp&quality=80&width=600&height=345",  "부산", "부산광역시 해운대구 구남로 187", 31000, "활동");
        tourismService.create("부산시립미술관" , "https://goguides.azureedge.net/media/tgxjptki/2ddbe82f-4a76-4320-81cf-031d5b93cdd1.jpg?anchor=center&mode=crop&width=1600&height=1066&quality=50",  "부산", "부산광역시 중구 남포로 67번길 25", 5000, "문화");
        tourismService.create("국립해양박물관" , "https://www.mmk.or.kr/mobile/_public/images/knmm/s5_3_2_img1_3.jpg",  "부산", "부산광역시 해운대구 우동 1503", 0, "문화");
        tourismService.create("브레이크아웃 이스케이프 방탈출카페" , "https://mblogthumb-phinf.pstatic.net/MjAyMjA2MDRfMjg4/MDAxNjU0Mjk0MjcwODI1.x849Xxgtqg4VgAnBzn0qVusCPSw_6c9rgoeIsGTqOsog.i51uoM8RgiYBTusV41roTuNf8aWyMAq2EZMRL3RoJ2kg.JPEG.xcl_nr/20220603_130921.jpg?type=w800", "부산", "부산광역시 해운대구 센텀2로 25번길 3, 2층", 24000, "활동");
        tourismService.create("BIFF광장" , "https://a.cdn-hotels.com/gdcs/production36/d10/45f075e3-5b5a-46cc-8f68-4544c2a9d8b8.jpg?impolicy=fcrop&w=800&h=533&q=medium",  "부산", "부산광역시 중구 비프광장로 58", 0, "문화");
        tourismService.create("국립부산과학관" , "https://i.namu.wiki/i/_U0gtDTHEPO8Ldq8wIrd-vRpy4mFdZEudzO9ganliUnlSEDh3O_CLK0cKI-66nncg7UyZWonKlqCfLf7PitQjw.webp", "부산", "부산광역시 금정구 부산대학로 63번길 2", 3000, "문화");
        tourismService.create("부산현대미술관" , "http://www.busanbiennale2022.org/media/pages/exhibition/places/mocabusan/5a3dc23161-1659087528/busanhyeondaemisulgwan.jpg",  "부산", "부산광역시 사하구 당리로 235번길 37", 12000, "문화");
        tourismService.create("런닝맨 부산점" , "https://www.visitbusan.net/uploadImgs/files/cntnts/20240426152204562_wufrotr",  "부산", "부산광역시 부산진구 부전동 227-2 삼정타워 10층", 16000, "활동");
        tourismService.create("좌동재래시장" , "https://busan.grandculture.net/gallery/busan/picture/s/GC042P21015_s.jpg",  "부산", "부산광역시 동래구 좌동1로 11", 0, "문화");
        tourismService.create("경복궁", "https://search.pstatic.net/sunny/?src=https%3A%2F%2Fpreviews.123rf.com%2Fimages%2Fnitiruj%2Fnitiruj1305%2Fnitiruj130500001%2F20016668-%25EA%25B2%25BD%25EB%25B3%25B5%25EA%25B6%2581.jpg&type=a340", "서울", "서울 종로구 사직로 161", 3000, "문화");
        tourismService.create("국립중앙박물관", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA3MThfNTEg%2FMDAxNzIxMjM0OTM2MTA3.nqmqf856rksrNWl1cITIIuYDmorS7ug0UuQrZPRNK-Qg.IR0PQJnfvJW2621k0LhGKteLSSHVfkHgLhPCrFd1CNcg.JPEG%2FIMG_3107.JPG&type=l340_165", "서울", "서울 용산구 서빙고로 137", 0, "문화");
        tourismService.create("북촌 한옥마을", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAzMDRfMjQ5%2FMDAxNzA5NTUyMDU4NTQz.QVaCIGwOvEWm_3yzcyq1WACvyfS3CsDFm8y2L-8DhnEg.BsWrsb_wGUwyzm_OM0xvm1-fZbkzQnHT4Q_kXciW_Ecg.JPEG%2F%25B4%25D9%25BF%25EE%25B7%25CE%25B5%25E5.jpg&type=a340", "서울", "서울 종로구 계동길 37", 0, "문화");
        tourismService.create("덕수궁", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDEwMjlfMjcg%2FMDAxNzMwMTg3ODgzOTIx.8C1Da4WHxjBBYUb0j2A5ZifAuM9Ql9J3YjM440Bh3hkg.ka27AraLMu-bo8vAL-A3hnadLbxj_Xnl6fs8mE0RN9Ug.PNG%2F%25B1%25B9%25B0%25A1%25C0%25AF%25BB%25EA%25C3%25A4%25B3%25CE_%25C0%25AF%25BB%25EA%25BC%25D2_%25B4%25F6%25BC%25F6%25B1%25C3_3%25C6%25ED.mp4_000004738.png&type=a340", "서울", "서울 중구 세종대로 99", 1000, "문화");
        tourismService.create("롯데월드", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA4MjFfMjY0%2FMDAxNzI0MjQ1NDM1OTk4.DsEdOzHcOxgthiG0MOnKkVWmWwsQnTzY6hIa3hvKAvYg.EpX9L6qLruXCbvntfmqSK-1tcFnaFe7pDy2HHMdEEhgg.JPEG%2FIMG_6636.jpg&type=a340", "서울", "서울 송파구 올림픽로 240", 57000, "활동");
        tourismService.create("N서울타워", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20151003_13%2Fmoonwindtree_14438364149917Ja3B_JPEG%2FIMG_5575.jpg&type=a340", "서울", "서울 용산구 남산공원길 105", 15000, "활동");
        tourismService.create("서울숲", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA5MTZfMjMy%2FMDAxNzI2NDc0ODAyMzAz.SnM5_hLrMKGaVCVombYcBlovZZn5wXi6n3NhP9JomzEg.C-U-oeMpoRg_1OflcW2uBDXuWGU-eHldzWdktBFE_z4g.JPEG%2F%25BC%25AD%25BF%25EF%25BA%25E4%25C6%25BC%25C6%25AE%25B7%25A1%25BA%25ED%25C0%25A7%25C5%25A9_%25285%2529.jpg&type=a340", "서울", "서울 성동구 뚝섬로 273", 0, "활동");
        tourismService.create("홍대 VR 스테이션", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTA1MjBfNzEg%2FMDAxNTU4MzYyNjg1NTM0.68pkaOuZ7e2yZaZVGDNVqgrGq0Og2BYf2JAnMvA5X_wg.s15U8U8ICfWZ_M0SZxl0DzzrGX-1Xt6eNfy-FxAj-cMg.JPEG.jihye7822%2FIMG_7031.jpg&type=a340", "서울", "서울 마포구 양화로 166", 20000, "활동");
        tourismService.create("남산공원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA1MDFfMTg0%2FMDAxNzE0NTQwNzAyNTYy.PK0ZzKRHho-W9vHT1Auy4lkkkD-DvYvKlygG9v5zAeMg.vZEnMixryoBGNdkTHpt-WMesaU9JlyGFiwGIlzK9Bukg.JPEG%2FIMG_6654.jpg&type=a340", "서울", "서울 중구 소월로 262", 0, "자연");
        tourismService.create("한강시민공원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20140606_276%2Fsjh4495_1402034401535bfzc2_JPEG%2F1402034399466_MyPhoto_1084997469_0625.jpg&type=a340", "서울", "서울 여의도동 330", 0, "자연");
        tourismService.create("서울창포원", "https://search.pstatic.net/sunny/?src=https%3A%2F%2Ft1.daumcdn.net%2Fthumb%2FR1280x0.fjpg%2F%3Ffname%3Dhttp%3A%2F%2Ft1.daumcdn.net%2Fbrunch%2Fservice%2Fuser%2F4JSG%2Fimage%2FJcYE49Xl-6KyTyYqmLPm7i00oyI.jpg&type=a340", "서울", "서울 도봉구 마들로 916", 0, "자연");
        tourismService.create("인왕산", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA1MDRfMjY1%2FMDAxNzE0ODE3NDg0MTE0.N-C-rsAKnoYt6Ik4KKYM43m6V32UiIA7HUU42NA0YMcg.U2BKJtdBw-t1-8V0KGxa_Ncov3ZtqKNkHLtL23yIxmkg.JPEG%2F20231029%25A3%25DF121538.jpg&type=a340", "서울", "서울 종로구 통인동 산1-72", 0, "자연");
        tourismService.create("송도베이스테이션(송도해상케이블카)", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxODA4MDJfNjcg%2FMDAxNTMzMjE1NjkzNDE4.QRqxHSTy6jjFdKPm8Uy-fYo2T-PEujIvsI1W9qVUCscg.iER9LIe73s1UCoNIB6e1cqISWyictuz-52f4VoBeiawg.JPEG.bada3347%2F008.JPG&type=a340", "부산", "부산광역시 서구 아미동 산 17-1", 16000, "자연");
        tourismService.create("아홉산숲", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA2MThfMjg4%2FMDAxNzE4NjU5MDg3NDk4.jxCwHQcH4WBYfH6kzWQHETSE4oC9mHMzZZhjJcuQHo8g.Gh6J6K5aucwjPz-RDK72suDv8GZ2xHI-8JrosxWHsVsg.JPEG%2FKakaoTalk_20240618_060348788_06.jpg&type=a340", "부산", "부산광역시 금정구 천마로 250", 5000, "자연");
        tourismService.create("오륙도 스카이워크", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA2MTlfNTgg%2FMDAxNjg3MTg0MjM4ODg0.r3MU94gpX1Qjpy5gOcTsvBIP2tv39HgTq-go_Prdl6Qg.BU4VlECuYqoASCW9aFaHEIINzteiwoqQhYbyK_C3JsAg.JPEG.gina171%2F1687184237935.jpg&type=a340", "부산", "부산광역시 해운대구 중동 산 116-1", 0, "자연");
        tourismService.create("해운대해수욕장", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDEwMTVfMjYx%2FMDAxNzI4OTkyNjY4ODAy.02YHC8VsY9SfPwe-BYiDdZQ3OEH3UGOaxMA6dVZocKEg.fWVO7naK3MMnadsSxJWQKSSkzzUYkxQ0cPK-Ww5Lk4cg.PNG%2Fimage.png&type=l340_165", "부산", "부산광역시 해운대구 해운대해변로 264번길 41", 0, "자연");
        tourismService.create("제주민속촌", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAyMDdfNjIg%2FMDAxNzA3MjY2NzQzNjIy.szLOfTvDfoN-nfzmfdwPmptKc5z3Dd5E4rsbM8gLuj0g.4ioSeT_8EUAf6okESZ4AXv1IhGeiAyAru0U_zc1bNVcg.PNG.shinhwavilla%2Fimage.png&type=l340_165", "제주도", "제주 서귀포시 표선면 민속해안로 631-34", 15000, "문화");
        tourismService.create("제주 현대미술관", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDEwMjRfMjE0%2FMDAxNzI5NzYwMjk4Njgx.pbEzDgqMNuz4AaGUqJBimTUZzTEvMYB00xnOTWNkTKYg.RqRPyu9hi2L46YG1da-3BUUUdplDquksvAzAdG4k9Ogg.JPEG%2F2.jpg&type=a340", "제주도", "제주 제주시 한경면 저지14길 35", 2000, "문화");
        tourismService.create("성읍민속마을", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA3MDlfNjMg%2FMDAxNzIwNDUxMjI2Njk4.efmK1MB5SuRd7S2NcYdzeiT7rR4mnvoWGaZUkCViP5Ug.IA7F4t80aVeeOOUoXMXPULIqRYj1k3DW9y_VlQw0xCMg.JPEG%2Fe20.jpg&type=a340", "제주도", "제주 서귀포시 표선면 성읍정의현로 19", 0, "문화");
        tourismService.create("이중섭 미술관", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA2MDZfMjA2%2FMDAxNjg2MDI5Mzk4MDg3.3M16XppPZffBB165yt-MtVt6BSLL8xjRXjTkVyPcgjgg.IKxo2DHDubF6QqqO14xHqdFEJrFhqe0RPEqatd1Onnwg.JPEG.kooni%2F%25C0%25CC%25C1%25DF%25BC%25B700.jpg&type=a340", "제주도", "제주 서귀포시 이중섭로 27-3", 1500, "문화");
        tourismService.create("제주바다하늘패러투어", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA4MDNfMjMx%2FMDAxNjkxMDQ3NjY5NTkw.4YMyz4RPVCzbcOhKApnk8Bx3bt4ibbo4SYju_hPaWgAg.VuaT4wXibdKBHfn_1waw-_Rs8YTGLCdZolOn-yO-fMQg.JPEG.heavensent-happy2%2FKakaoTalk_20230803_161353726_03.jpg&type=a340", "제주도", "제주 제주시 한림읍 한창로 1295", 100000, "활동");
        tourismService.create("한라산 등반", "https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5887%2F2024%2F07%2F09%2F0000011718_001_20240709161614594.jpg&type=l340_165", "제주도", "제주 서귀포시 토평동 산15-1", 0, "활동");
        tourismService.create("섭지코지 승마 체험", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjExMjNfMjAz%2FMDAxNjY5MTk4NjQ1NTQy.XLR7MUefYvKxjYk412leXTOmnU79j19AFibyklkUwkgg.OS1Cqt3WHRcOnbnXjWbhA4XzF2JuynA6mjzuMA_FyOwg.JPEG.kce0531%2FIMG_3871.JPG&type=a340", "제주도", "제주 서귀포시 성산읍 섭지코지로 50", 30000, "활동");
        tourismService.create("제주 해양레저 스포츠", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA2MDhfOTUg%2FMDAxNjIzMTI3ODcwMjAw.Ceh0L7H78xJ4uYjTVqYhKHtHa6u8AsgY6CrSXOJxQXcg.wg1lkMD8TTmr3z8PgpRwkP7C2eI-W1HL9JRgIK-nVNYg.JPEG.yubin0304%2F20210604%25A3%25DF145724.jpg&type=a340", "제주도", "제주 제주시 이호일동 374-4", 50000, "활동");
        tourismService.create("성산일출봉", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA1MjZfMjA1%2FMDAxNzE2NzI5MjA1MDU2.fhq4UEANQIQ3vM5pBeKZcf__JovFlZD1TTB8JI-EXYUg.K3oIR4STemkNqVvqu97JBgX2tmXwjbbjVc1fAYJ5BbEg.JPEG%2F20240524%25A3%25DF102016.jpg&type=a340", "제주도", "제주 서귀포시 성산읍 일출로 284-12", 2000, "자연");
        tourismService.create("만장굴", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzEwMDlfMjI1%2FMDAxNjk2ODI3NTE2MzM1.RUp_zWmpSBtrf6eZnXlK7KAULS4Cbn5rgBqpBZKmg4Ug.CJrPQsal6cJWcDsRqYeWoOfiR_7xtX_4i0s10pprEpIg.JPEG.honestsg%2F20230903_155440.jpg&type=a340", "제주도", "제주 제주시 구좌읍 만장굴길 182", 2000, "자연");
        tourismService.create("우도", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA2MDRfMTk0%2FMDAxNzE3NDg2NjI5Mzgx.w2mUb6QbPxc3VIXCLOSYYJkbxCYxZSmZMGuate-AXa8g.0iUX7s2w-Se1pHO9fbXfKWAbNNZyyAMzRgoB5K8C4cog.JPEG%2F9_%25BF%25EC%25B5%25B5_%25B0%25A1%25BA%25BC%25B8%25B8%25C7%25D1%25B0%25F7_%25281%2529.JPG&type=a340", "제주도", "제주 제주시 우도면", 8000, "자연");
        tourismService.create("천지연 폭포", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDEwMjJfODkg%2FMDAxNzI5NTcyOTQ0OTk4.UJdGa5mjlYeZwXHkJ-A5sBJIYessPumCTzQq_AjfNMYg.E1M0Qn1-d8n_R3-he34-iVbVwbxBsFequg_QzP4vWmwg.JPEG%2F20241010%25A3%25DF104015.jpg&type=a340", "제주도", "제주 서귀포시 천지연폭포길 22", 2000, "자연");
        tourismService.create("국립중앙과학관", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAxMDJfMjQz%2FMDAxNzA0MTkwMDgzMzgx.ZsUZJMfkYDc9xDeLKEOxfOg41hEQn5fAUb_P-WB_8igg.4U0BANhqFDP7QyV8C9cxAQq1MwkDruCRZcewpFHg5wIg.JPEG.firstchubasa%2FIMG_5210.jpg&type=a340", "대전", "대전 유성구 대덕대로 481", 0, "문화");
        tourismService.create("대전예술의전당", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20150805_179%2F740329nam_1438755028892TloVT_JPEG%2FP20150731_101124853_CA84EB70-6A5B-4F70-961D-F4234BCF71D6.JPG&type=a340", "대전", "대전 서구 둔산대로 135", 0, "문화");
        tourismService.create("대전근현대사전시관", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20150320_96%2Fpaichaiuniv_1426809930096O2eRP_JPEG%2F_MGL0947.jpg&type=a340", "대전", "대전 중구 대흥동 396-1", 0, "문화");
        tourismService.create("대전시립미술관", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAzMTFfMjMx%2FMDAxNjc4NTAwMDQ2NjIw.uKkWKG7YO2cZHTWkiY45DCLZmZ6m1U2LjIs6zjTl4Ggg.FdQukmwku1t2Qra8nYIh79oFV9jW2v5EHDKyiAdbAg4g.JPEG.storydaejeon%2F1_%25B4%25EB%25C0%25FC%25BD%25C3%25B8%25B3%25B9%25CC%25BC%25FA%25B0%25FC_%25C7%25F6%25B4%25EB%25B9%25CC%25BC%25FA%25B1%25E2%25C8%25B9%25C0%25FC_%25C1%25B6%25B0%25A2.%25B0%25F8%25B0%25A3.%25C6%25DB%25C1%25F1.jpg&type=a340", "대전", "대전 서구 둔산대로 155", 5000, "문화");
        tourismService.create("엑스포과학공원", "https://search.pstatic.net/sunny/?src=http%3A%2F%2Fcfile10.uf.tistory.com%2Fimage%2F220CDF4255545A0F0DFDC9&type=a340", "대전", "대전 유성구 대덕대로 480", 0, "활동");
        tourismService.create("계족산 황톳길", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA2MDZfMTQy%2FMDAxNzE3Njc3MTEyMDcx.u34Ej1g2Jwr2_sbr4KQ32XEIBrLhMOGaCUHCvrKB_dQg.xV5-XbZlTdOBiCa6VtgoWv1KbMPWc1QAA1SYcPWUiXAg.JPEG%2F20240606%25A3%25DF131610.jpg&type=l340_165", "대전", "대전 대덕구 장동 22-1", 0, "활동");
        tourismService.create("한밭수목원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA5MTdfMTQ0%2FMDAxNzI2NTYxMTg1NzYz.Kd9BCb_Zm5cJ5lTsjhPVdpK_84NebmSoDkEDkyFMN3sg.-LPEOPgcVcpr_3QL0Kj7N24q14nVUSEsW3LUFAudShEg.JPEG%2F20240714%25A3%25DF182607.jpg&type=a340", "대전", "대전 서구 둔산대로 169", 0, "활동");
        tourismService.create("뿌리공원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDExMDFfMjEz%2FMDAxNjA0MTkwMzcxOTI3.rFhgtHmMaaHPvgTwkYvCOsMY9U3tJf-Miv-xRuhNNEcg.3AwkTBukMEmKa3fGLT6C5Nw_2qChOEcHXxEpo29e194g.JPEG.djjunggu8%2Fa_32d5e2_h_e95Ud018svc1fm5xbz597q1i_9xlmox.jpg&type=a340", "대전", "대전 중구 뿌리공원로 79", 0, "활동");
        tourismService.create("계룡산 국립공원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzAyMDFfMTcg%2FMDAxNjc1MjIzMjk2MTA0._9P0DBuVBJUpAQBXSHf0D1JNXxEC-LMyf9c8YeKSb10g.CtmrvT6kMc9MQpX3RbCtD6Dkq6KuuOhF40DLyYvKVb8g.JPEG.kimyu1117%2FIMG_5755.jpg&type=a340", "대전", "대전 유성구 계룡산로 607", 0, "자연");
        tourismService.create("유림공원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA0MTRfNjYg%2FMDAxNTg2ODQ0Njk4OTgy.oSiHsWNRK2zDl788FPnxIVSLTzlj3MLfZpKudL0dWB4g.WrN_ccVzfI6O-XCTQ4muajgnqIYyD9HHqp3RMnjYt8Ig.JPEG.yuseonggu%2F3F3A5877.JPG&type=a340", "대전", "대전 서구 둔산로 100", 0, "자연");
        tourismService.create("갑천생태공원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA0MTdfMjQ0%2FMDAxNjUwMTc3MzIyMjM5.KrlqYh6c--Lfgp0S18d4quT6CC_WixLS9XSfVWBvyFUg.Mv9Va1UcCjdCflv8jy9exHc126Esnue1vXYJ3c1njXog.JPEG.miky_perky%2F1650177217587.jpg&type=a340", "대전", "대전 서구 월평동", 0, "자연");
        tourismService.create("대청호반", "https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F5285%2F2016%2F10%2F26%2F48981_68097_84_99_20161026093043.jpg&type=a340", "대전", "대전 대덕구 대청로 618", 0, "자연");
        tourismService.create("전주 한옥마을", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAxMDRfNDgg%2FMDAxNzA0Mzc5Njg3NjI4.A5Bh_T-l4Ec6F9swRXhw5LZvrfLTUrGyrNKxgVl5ytQg.rdIhC-_d6mNHgv9q3s3-ytpMgptXx5RnggDacf6tCVAg.JPEG.biocau%2FIMG_0358.JPG&type=a340", "전주", "전북 전주시 완산구 기린대로 99", 0, "문화");
        tourismService.create("전주 향교", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzExMDZfMTg2%2FMDAxNjk5MjQ2NjEyMzEz.1Gb6bXfB675ncOpLYBB1GGNhlZAImtTWrYYMQei3kssg.UIWhc4P60okhLbCYqVMEMx2gkTADN0kFojvenzNEgR8g.JPEG.rokmc153%2Fd3713d9fe43a1ec18c1bfbbc4ec671c9.jpg&type=a340", "전주", "전북 전주시 완산구 교동 26", 0, "문화");
        tourismService.create("국립무형유산원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fimgnews.naver.net%2Fimage%2F001%2F2013%2F06%2F13%2FPYH2013061307380005500_P2_59_20130613150911.jpg&type=a340", "전주", "전북 전주시 완산구 서학로 95", 0, "문화");
        tourismService.create("전주 전동성당", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDEwMzFfMTgz%2FMDAxNzMwMzQ4MTcwMzk1.g00feuyRAo8TEQlYyUioCbGaG71zdchor_EN8qsN4tUg.sBjP3z2b3SHqR5vSiHdqH5m4bQsoVHsrnVB6fz0eohcg.JPEG%2F20241013%25A3%25DF114553.jpg&type=a340", "전주", "전북 전주시 완산구 태조로 51", 0, "문화");
        tourismService.create("오목대와 이목대", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzEwMjJfMTg4%2FMDAxNTA4Njc2NzcxNzk5._JIvoMJeLxMkUhaIYMOK6I0Mg_SGqi_gGbFZvjr9RRwg.1nA3PAjbrHsoIwx2TqFMJ3uYWN9YC_M5GM2lVvCmkf4g.JPEG.spirit0628%2F20171022_134146.jpg&type=l340_165", "전주", "전북 전주시 완산구 기린대로 55", 0, "활동");
        tourismService.create("전주 한지문화관", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzA0MDlfMTY0%2FMDAxNDkxNzIzMTMzNzUz.NX-yS2erCMDbBYswK5dykVsohmHgJjOIWRdKHtMIBSgg.IHj89GBzWggM9qpDdWVk6VfLjxIatUBqa1TKAfRrPm4g.JPEG.sochonn%2FIMG_0805.JPG&type=a340", "전주", "전북 전주시 완산구 현무1길 20", 5000, "활동");
        tourismService.create("전주 종이문화 테마파크", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA1MTZfMjA5%2FMDAxNjUyNjgwMjU5OTEz.v9c1rWA9g7W6iN7Dawu6VXCDvXpUkyOB9-H5a52FcTEg.bYXr8P-XBkCZPBGBWkplF_YpV1rkfX9wbG_EzkteWAUg.PNG.gwdoraeyo%2F6.png&type=a340", "전주", "전북 전주시 덕진구 솔내로 46", 3000, "활동");
        tourismService.create("전주난장", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA0MjhfMjYx%2FMDAxNzE0MzAyODc2OTc1.Ov9OnautQPeNY0WWjd3bh034vstqsZ_ZDqTeFVKAqsIg.hu6A16lq86t0hlTMzKAXGeGHFgHOkUdBb7syehMZgGUg.JPEG%2FKakaoTalk_20240428_151510606_21.jpg&type=a340", "전주", "전북 전주시 완산구 풍남문1길 15", 0, "활동");
        tourismService.create("덕진공원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA5MjJfMTc3%2FMDAxNzI3MDA1Mzk0NzEw.3a42ucgusiUNXdB-tmmcUTis_GbSs1y00gqeu8Ni_Hkg.TJLhTjzS2OCX4Gnvdf7Fb32QGDXFfJYUxiSVfBLJY8kg.JPEG%2FAAA_1870.JPG&type=a340", "전주", "전북 전주시 덕진구 권삼득로 390", 0, "자연");
        tourismService.create("완산공원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2F20150422_80%2Fjcjkks_1429704349959krTuq_JPEG%2F20150422-DSC06120.jpg&type=a340", "전주", "전북 전주시 완산구 전동3길 4", 0, "자연");
        tourismService.create("모악산 도립공원", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMDA1MDZfOCAg%2FMDAxNTg4NzY5NjE0NjE0.KOwI_DdEkUZeSmsmho-hO-idCAgZe1r0nU3hOVTTH_gg.HlzSX98C--IKYDGyXMqXTqjYu0BcQFzliO0p5C2SSHAg.JPEG.nicetoptop%2F1588769614268.jpg&type=a340", "전주", "전북 전주시 덕진구 모악로 302", 0, "자연");
        tourismService.create("전주천", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzExMDNfMjgy%2FMDAxNjk4OTc3MTg0MzE3.GzILal1ll98DJg0WD1ck3Uhma1CPt075lXAuRUxOrTUg.4PyM39bPMoZ2QSDcnV2PknU4WNs34nbxlFYROYfJlhUg.JPEG.syj08027%2FB612%25A3%25DF20231102%25A3%25DF120346%25A3%25DF966.jpg&type=a340", "전주", "전북 전주시 완산구 대성동 1030-1", 0, "자연");
        tourismService.create("오죽헌", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDA4MjBfMTk0%2FMDAxNzI0MTEyMDM0NDE4.bRrVqgzvyGMXKm4HwevT-pR2l036lhmPubNb9S8Ldscg.Y1fmzCKHxpXk6ntpZdwXSNg_iPQ1nsQF-ZaGuAe0tyIg.PNG%2Fimage.png&type=a340", "강릉", "강원 강릉시 율곡로 3139", 3000, "문화");
        tourismService.create("강릉 선교장", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA2MTFfMjAw%2FMDAxNjg2NDM2MjkxNzI5.cQri0LlTdd0s12d1r8StYAs9ZyBDK0Bgy6Zkaa4e4zAg.OIgzZCbs0GgjjgM7HqmwGq54lQ7jad8EArDh0npyUHcg.JPEG.lhj59%2FIMG_4996-1.jpg&type=a340", "강릉", "강원 강릉시 운정길 63", 5000, "문화");
        tourismService.create("하슬라 아트월드", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAyMjZfMTA3%2FMDAxNzA4OTI3NzUyMTI3.V_7CvJJPy_sAOpcKcNKIsTaTumvol7ha5nyLzMR7EaQg.cjuPzT3EozErDmwKC2FW2GhlNlFIxRjCmGPtoy_Ix2og.JPEG%2FIMG_3088.JPG&type=a340", "강릉", "강원 강릉시 율곡로 1441-1", 10000, "문화");
        tourismService.create("강릉 단오제 전수교육관", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTA5MDJfMjYy%2FMDAxNTY3NDAxNDI0NzE1.uUIsAD0_fqwRpSftUSSbiYizlzaCCeLqUJ3Wy8Fw9SIg.gYI_qLCP_CvOulj-_PQqc1CPyhAABeQHsyt0nzKWea0g.JPEG.pinegn%2F1.JPG&type=a340", "강릉", "강원 강릉시 단오장길 1", 0, "문화");
        tourismService.create("정동진 레일바이크", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA5MTFfMTIw%2FMDAxNjk0NDE5ODg0ODA4.bb_cIjwih66Gew3E1lm1abcZHRBVo3eMSZxZ7CTTzlUg.bL3Sh4SPpV7h2Gkq3DhZJkROf6BPjWAWNMZEAF8CKMUg.JPEG.docterfish%2F1694419068030.jpg&type=a340", "강릉", "강원 강릉시 강동면 헌화로 455", 20000, "활동");
        tourismService.create("경포 아쿠아리움", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMjA1MDlfMjM2%2FMDAxNjUyMDkyNzU0ODUx.P4n8W-fjABPNiWtKByyAn3FfLtwptchf8_oyoR6ENLYg.p_knWd972fzB3V8v1mhZi9WzuQTm_AmcwwkSCDloVDkg.JPEG.gmlwofl23%2F%25B0%25AD%25BF%25F8%25B5%25B5_%25B0%25AD%25B8%25AA_%25B0%25E6%25C6%25F7_%25BE%25C6%25C4%25ED%25BE%25C6%25B8%25AE%25BF%25F2_%252834%2529.jpg&type=a340", "강릉", "강원 강릉시 난설헌로 131", 25000, "활동");
        tourismService.create("강릉 중앙시장", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDAxMTRfMjgw%2FMDAxNzA1MTg5MjcyODA4.eELwFcNo6uLYWdZPRAkATWx_HGDK49OgRrzXPb_NmeQg.1MIUXI4vTsSTIRf3_ZVLp5b-3ngpopU0FfhGP2xNaOkg.JPEG.azsmile%2Fgang_62.jpg&type=a340", "강릉", "강원 강릉시 금성로 21", 0, "활동");
        tourismService.create("안목 커피거리", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMTA4MTZfMTc0%2FMDAxNjI5MDQwMzcwNjYy.pgookW38EZl-lIoYN300OPPrK4RGvo8mc6d56Yfx7Pgg.43f28LIeteoHcD-Urvy9f-gNLpBNk53WcMJIV88kYtcg.JPEG.helloelie%2F20210804_115555.jpg&type=a340", "강릉", "강원 강릉시 창해로 17", 0, "활동");
        tourismService.create("경포대", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyMzA4MDhfMTU4%2FMDAxNjkxNDY1MjEwODQx.o4r_DbDvpj4tXBQbzImKqRuo0qeV0Fxr7RPUJmkUQtYg.84IPy7DHUsQeXs4QAjwXgDGPelw-lumL0OVMKTCxg7Ag.JPEG.masterauction1%2F01_t.jpg&type=l340_165", "강릉", "강원 강릉시 경포로 365", 0, "자연");
        tourismService.create("정동진 해변", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTAzMTlfNTIg%2FMDAxNTUzMDA2ODgzMjkw.erZQEDI63OClh-P_oykZSRPwSmdFExDUGqgocTtzFFUg.uMt62CY1-qnV3TOJ51I_KYGij6ZnzNU5SfKlX9vg0xYg.JPEG.blogtotal%2F004.jpg&type=a340", "강릉", "강원 강릉시 강동면 정동역길 17", 0, "자연");
        tourismService.create("주문진 해변", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxOTA0MDFfMTc4%2FMDAxNTU0MTAzNzcyNTU3.1zVhRm_9yrt2PiFGLlOqLCu9C28xNvqzdT2moJszIscg.UsTg8RYiuMvBUJHsMUDzMtBQ2fZHI7xmOswdxlRtYjwg.JPEG.weeeunjee%2FDSCF5925.jpg&type=a340", "강릉", "강원 강릉시 주문진읍 해안로 1758", 0, "자연");
        tourismService.create("소금강", "https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAxNzEwMjNfMTg4%2FMDAxNTA4NzU0NDIzOTA0.ISwtwTsBuMJRLiOndlZi6lcA12-DeE2zbt2xzp-qNeQg.s0vy_l6oJOoaIaK2wGZbFskvoUmqJ0Di-vri9IXQQFkg.JPEG.mwsys%2FIMG_1927_copy.jpg&type=a340", "강릉", "강원 강릉시 연곡면 삼산리", 0, "자연");
    }
}
