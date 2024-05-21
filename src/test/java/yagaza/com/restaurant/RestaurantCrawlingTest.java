package yagaza.com.restaurant;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
    public void RestaurantCrawling(){
        Set<String> name = new LinkedHashSet <String>();
        Set<String> restaurantUrl = new LinkedHashSet <String>();
        List<String> content = new ArrayList<>();
        List<String> price = new ArrayList<>();
        int count = 0;

        for(int i = 0; i < 4 ; i ++){
            try {
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                WebDriver driver = new ChromeDriver();
                String url = "https://app.catchtable.co.kr/ct/search/list?showTabs=true&location=CAT026_CAT026001_CAT026002_CAT026003_CAT026004_CAT026005_CAT026006_CAT026007_CAT026_CAT026001_CAT026002_CAT026003_CAT026004_CAT026005_CAT026006_CAT026007&uniqueListId=1715488404675&foodKind=D_16&foodKind=E_43&foodKind=E_45&foodKind=E_84&foodKind=E_86&foodKind=E_89";
                driver.get(url);
                Thread.sleep(2500);
                var stTime = new Date().getTime();

                while (new Date().getTime() < stTime + 35000) { //30초 동안 무한스크롤 지속
                    List<WebElement> restaurantElements = driver.findElements(By.className("restaurant-info"));
                    System.out.println(restaurantElements.size());

                    for (WebElement restaurantElement : restaurantElements){
                        System.out.println("이미지 주소는 : " + restaurantElement.findElement(By.className("img")).getAttribute("style"));
                        restaurantUrl.add(getImgto(restaurantElement.findElement(By.className("img")).getAttribute("style")));

                        System.out.println("가게 이름은 : " + restaurantElement.findElement(By.className("name")).getText());
                        name.add(restaurantElement.findElement(By.className("name")).getText());

                        System.out.println("내용은  :" + restaurantElement.findElement(By.className("excerpt")).getText());
                        if(name.size() != count){
                            content.add(restaurantElement.findElement(By.className("excerpt")).getText());
                        }

                        System.out.println("가격은 : " + restaurantElement.findElement(By.className("price")).getText());
                        if(name.size() != count){
                            price.add(restaurantElement.findElement(By.className("price")).getText());
                        }

                        count = name.size();
                        System.out.println("set의 size : "+ count);
                        System.out.println();

                    }
                    Thread.sleep(2000); //리소스 초과 방지
                    ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
                }
                System.out.println(name.size());
            }catch (Exception e){
                System.out.println("오류발생");
                e.printStackTrace();
            }
        }
        Iterator<String> nameIterator = name.iterator();
        Iterator<String> urlIterator = restaurantUrl.iterator();
        Iterator<String> contentIterator = content.iterator();
        Iterator<String> priceIterator = price.iterator();

        System.out.println(name.size() +", " + restaurantUrl.size() + ", " +
                content.size()+", "+ price.size());
        String priceIndex;
        String restName;
        String contentIndex;
        String urlIndex;
        int flag = 0;
        while(nameIterator.hasNext()){
            flag = 0;
            priceIndex = priceIterator.next();
            restName = nameIterator.next();
            contentIndex = contentIterator.next();
            urlIndex = urlIterator.next();
            System.out.println("nameIterator = " + restName);
//            System.out.println("urlIterator = " + urlIndex);
//            System.out.println("contentIterator = " + contentIndex);
            System.out.println("priceIterator = " + priceIndex);
//            System.out.println("doubleToInt(getPrice(priceIndex) = " + Arrays.toString(doubleToInt(getPrice(priceIndex))));
//            System.out.println("getOpenTime(priceIndex) = " + getOpenTime(priceIndex));
//            System.out.println();

            for(Restaurant restaurant : this.restaurantRepository.findAll()){
                if(restName.equals(restaurant.getName())){
                    flag = 1;
                }

            }
            if (flag == 1){
                continue;
            }
            restaurantService.create(restName, contentIndex, "기타 세계 음식", urlIndex,
                    doubleToInt(getPrice(priceIndex)), getOpenTime(priceIndex));
        }
    }

    @Test
    public void getImgToTest(){
        String str = "background-image: url(\"https://image.toast.com/aaaaaqx/catchtable/shopinfo/swP-96takmlxejVtgZDPRzg/wp-96takmlxejvtgzdprzg_2312901253969752_thumbMenuImage.jpg?small180\"); overflow: hidden;";
        getImgto(str);
    }

    public String getImgto(String str){
        Pattern pattern = Pattern.compile("\\(\".*?\"\\)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            str = matcher.group();
            str = str.replaceAll("\\(" ,"");
            str = str.replaceAll("\\)", "");
            str = str.replace("\"", "");
        } else {
            System.out.println("No URL found.");
        }
        return str;
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
                System.out.println(price[0] +", "+ price[1]);
                return price;

            }else {
                if(matcher.find()){
                    price[0] = Double.parseDouble(matcher.group(0));
                    price[1] = Double.parseDouble(matcher.group(0));
                    System.out.println(price[0] +", "+ price[1]);
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
