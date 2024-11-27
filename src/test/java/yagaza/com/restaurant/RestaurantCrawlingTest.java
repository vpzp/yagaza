package yagaza.com.restaurant;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootTest
public class RestaurantCrawlingTest {
    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Test
    public void scrollTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        String url = "https://app.catchtable.co.kr/ct/map/COMMON?showTabs=true&bottomSheetHeightType=FULL_LIST&serviceType=INTEGRATION&keyword=%EB%B6%80%EC%82%B0&keywordSearch=%EB%B6%80%EC%82%B0&isShowListLabelExpanded=1&zoomLevel=11&centerBoundsLat=34.75064277788241&centerBoundsLng=129.08379544898582&isSearchKeywordComplete=1&isNewSearchInMap=1&isShowMapSearchButton=1&isScrollTop=1&location=CAT026_CAT026001_CAT026002_CAT026003_CAT026004_CAT026005_CAT026006_CAT026007&foodKind=C_13&foodKind=C_22&foodKind=C_18&foodKind=C_17&foodKind=C_23&foodKind=C_24&foodKind=C_16&foodKind=C_15&foodKind=C_25&foodKind=C_12&foodKind=C_2&foodKind=C_3&isSearchedInMap=0&uniqueListId=1731634791630";
        String type = "기타 세계음식";
        String region = "부산";
        driver.get(url);
        Thread.sleep(5500); // 페이지 로드 대기

        WebElement bodyElement = driver.findElement(By.cssSelector("body"));
        Actions actions = new Actions(driver);

        // 65초 동안 반복적으로 PAGE_DOWN을 사용하여 스크롤
        var stTime = new Date().getTime();
        List<String> nameList =  new ArrayList<>();
        List<String> priceList = new ArrayList<>();
        List<String> contentList = new ArrayList<>();
        List<List<String>> imgList = new ArrayList<>();
        while (new Date().getTime() < stTime + 40000) { // 30초 동안 스크롤
            List<WebElement> elements = driver.findElements(By.cssSelector("[id^='virtual_']"));
            for (WebElement element : elements) {
                try {
                    // 음식점 이름 추출
                   String name = (element.findElement(By.cssSelector(".ShopListItem_title__1p45wh65"))).getText();
                    if (nameList.contains(name) || name == ""){
                        continue;
                    }

                    // 가격 정보 추출

                    List<WebElement> priceElements = element.findElements(By.cssSelector(".IconText_text__6va2fh2"));
                    String price = priceElements.stream()
                            .reduce((first, second) -> second)
                            .map(WebElement::getText)
                            .orElse("");

                    //content 추가
                    List<WebElement> contentElements = element.findElements(By.cssSelector(".ShopHeaderInfo_text__1lnb8ff1"));
                    String content = contentElements.stream()
                            .reduce((first, second) -> second)
                            .map(WebElement::getText)
                            .orElse("");


                    // 이미지 URL 3장 리스트로 추출
                    List<WebElement> imageElements = element.findElements(By.cssSelector(".w9ce3a"));
                    List<String> imageUrls = new ArrayList<>();
                    for (int i = 0; i < Math.min(imageElements.size(), 3); i++) {
                        imageUrls.add(imageElements.get(i).getAttribute("src"));
                    }
                    if (imageUrls.isEmpty()) {
                        imageUrls.add("이미지 없음"); // 기본값 추가
                    }
                    nameList.add(name);
                    priceList.add(price);
                    contentList.add(content);
                    imgList.add(imageUrls);

                } catch (Exception e) {
                    System.out.println("정보 추출 중 오류가 발생한 요소: " + element);
                    e.printStackTrace();
                }
            }

            actions.moveToElement(bodyElement)
                    .sendKeys(Keys.PAGE_DOWN)
                    .sendKeys(Keys.PAGE_DOWN)
                    .sendKeys(Keys.PAGE_DOWN)
                    .sendKeys(Keys.PAGE_DOWN).perform();
        }

        contentList = getContent(contentList);
        System.out.println("elemnets의 size" + nameList.size());
        for (int i = 0; i < priceList.size(); i++) {
            System.out.println("음식점 이름: " + nameList.get(i));
            System.out.println("content 이름 : " + contentList.get(i));
            doubleToInt(getPrice(priceList.get(i)));
            System.out.println("오픈시각 : " + getOpenTime(priceList.get(i)));
            System.out.println("이미지 URLs: " + imgList.get(i).get(0));
            System.out.println("-----");
        }

        int flag = 0;
        for (int i = 0; i < nameList.size(); i++) {
            for(Restaurant restaurant : this.restaurantRepository.findAll()){
                if(nameList.equals(restaurant.getName())){
                    flag = 1;
                }

            }
            if (flag == 1){
                continue;
            }

            restaurantService.create(nameList.get(i), type, contentList.get(i), imgList.get(i),
                    doubleToInt(getPrice(priceList.get(i))), getOpenTime(priceList.get(i)), region);
        }
    }

    public double[] getPrice(String str){
        Pattern pattern = Pattern.compile("\\d+\\.?\\d*");
        Matcher matcher = pattern.matcher(str);
        double[] price = new double[2];
//        String str = "점심 저녁 동일가 8 - 9만원";
//        String str = "점심 저녁 동일가 3.98만원";
        if(str.contains("동일가")){
            if(str.contains("-")){
                int index = 0;
                while (matcher.find()) {
                    if(index > 1){
                        break;
                    }
                    price[index++] = Double.parseDouble(matcher.group());
                }
                price[0] = (price[0] + price[1]) / 2;
                price[1] = price[0];
                System.out.println("가격 : " + price[0] +", "+ price[1]);
                return price;

            }else {
                if(matcher.find()){
                    price[0] = Double.parseDouble(matcher.group(0));
                    price[1] = Double.parseDouble(matcher.group(0));
                    System.out.println("가격 : " + price[0] +", "+ price[1]);
                    return price;
                }
            }
        }
//      String str = "점심 영업안함 · 저녁 1 - 3만원";
        if(str.contains("영업안함")){
            double[] arr = new double[2];
            int index = 0;
            while(matcher.find()){
                if(index > 1){
                    break;
                }
                arr[index++] = Double.parseDouble(matcher.group());
            }
            price[1] = (arr[0] + arr[1]) / 2;
            System.out.println(price[0] + ", " + price[1]);
            return price;
        }
        int index = 0;

//        String str =  "점심 7.9만원 · 저녁 14.5만원";
//        String str = "점심 1 - 15만원 · 저녁 2 - 15만원";
        if(str.contains("-")){
            double[] arr = new double[4];
            while (matcher.find()) {
                if(index > 2){
                    break;
                }
                arr[index++] = Double.parseDouble(matcher.group());
            }
            price[0] = (arr[0] + arr[1]) / 2;
            price[1] = (arr[2] + arr[3]) / 2;

            System.out.println(price[0] +", "+ price[1]);
            return price;
        }else{
            try {
                while (matcher.find()) {
                    if(index > 1){
                        break;
                    }
                    price[index++] = Double.parseDouble(matcher.group());
                }

                System.out.println(price[0] +", "+ price[1]);
                return price;

            }catch (Exception e){
                e.printStackTrace();
                return price;
            }
        }
    }

    public int[] doubleToInt(double[] doubles){
        int[] ints = new int[doubles.length];
        for (int i = 0 ; i < doubles.length; i++){
            ints[i] = (int) (doubles[i] * 10000);
        }

        return ints;
    }
    public List<String> getContent(List<String> contentList){
        List<String> formantContent = new ArrayList<>();
        for (String content : contentList) {
            formantContent.add(content.substring(content.indexOf("·") +1));
        }

        return formantContent;
    }

    public List<String> getOpenTime(String str){
        List<String> openTime = new ArrayList<>();
        if(!str.contains("영업안함")){
            openTime.add("점심");
        }
        openTime.add("저녁");
        openTime.add("야식");
        return openTime;
    }
}
