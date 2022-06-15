package com.example.nbhwarehouse;

import java.util.ArrayList;

public class Utils {

    private static Utils instance;
    private static ArrayList<Kit_Model> allKits;

    public Utils() {
        if(null== allKits){
            allKits= new ArrayList<>();
            initInfo();
        }
    }

    private void initInfo(){
        //TODO: add initial info

        allKits.add(new Kit_Model(1, "صانع آيس كريم", "حفلات", "اضف جو لطيف ومميز إلى سهرتك مع أصدقائك", "https://nkprotelex.com/WebRoot/Store1/Shops/d4020cb0-5583-435d-acf1-32b6f8e9fad3/5CC5/AC01/E689/CDE6/CE60/0A48/3507/69CD/G58a_ml.jpg", 300));
        allKits.add(new Kit_Model(2, "ثلاجة رحلات", "مطبخ", "ثلاجة صغيرة تحفظ البرودة لمدة تصل إلى 16 ساعة", "https://cdn.salla.sa/oMc1a8SpQJUuxTxtu8XLEJpUbyFraLQru7BNb44x.jpeg", 300));
        allKits.add(new Kit_Model(3, "دراجة هوائية", "رياضة", "دراجات هوائية قوية التحمل", "https://www.arabgt.com/wp-content/uploads/2018/01/%D8%AF%D8%B1%D8%A7%D8%AC%D8%A9-%D8%AA%D8%B4%D9%8A%D8%B1%D9%88%D9%86.jpg", 300));
        allKits.add(new Kit_Model(4, "منشار كهربائي", "إصلاح وترميم", "منشار قوي يتحمل قطع الأشياء السميكة", "https://d2csxpduxe849s.cloudfront.net/media/2952086A-80D6-4590-85C8404E6BC2EBFC/5C4230AA-91AF-46A3-852CB67DDACBB3CB/High%20Res%20Zoom-17628.jpg", 300));
        allKits.add(new Kit_Model(5, "عجانة كهربائية", "طبخ", "عجانة حلوة", "https://cdn.salla.sa/BllAD/aW0MGNkGVAYLIDGoHK92PNWK9KHsTDqDK7rovUMa.png", 300));
        allKits.add(new Kit_Model(6, "شواية كهربائية", "حفلة شواء", "شواية كشخة للحفلات الشوائية والتنزه", "https://m.media-amazon.com/images/I/413Q9uswXSL._AC_SY1000_.jpg", 300));
    }

    public static Utils getInstance(){
        if(null != instance){
            return instance;
        } else {
            instance= new Utils();
            return instance;
        }
    }

    public static ArrayList<Kit_Model> getAllKits() {
        return allKits;
    }

    public Kit_Model getKitByID(int id){
        for (Kit_Model kit: allKits ) {
            if(kit.getId() ==id){
                return kit;
            }
        }
        return null;
    }
}
