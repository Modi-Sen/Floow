package com.java.Floow.technicalchallenge.service;

import com.java.Floow.technicalchallenge.bean.DriverBean;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.CREATE;

@Service
public class DriverService {

    Path p = Paths.get("file.txt");
    public static int id=0;
    public DriverBean addDrivers(DriverBean driverBean) {

        driverBean.setDriverId(id++);
        driverBean.setCreationDate(LocalDate.now());
      // isValidDateOfBirthFormat(driverBean.getDateOfBirth());
            String s = driverBean.toString() + "\n";
            byte data[] = s.getBytes(StandardCharsets.UTF_8);
            try (OutputStream out = new BufferedOutputStream(
                    Files.newOutputStream(p, CREATE, APPEND))) {
                out.write(data, 0, data.length);
            } catch (IOException x) {
                System.err.println("in catch write operation" + x);
            }
            return driverBean;
    }

    public List<String> findAll() {
        List<String> allDrivers=new ArrayList<>();
        try (InputStream in = Files.newInputStream(p);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            allDrivers = Files.lines(p.toAbsolutePath())
                    .map(t -> t.trim())
                    .filter(t -> !t.isEmpty()).collect(Collectors.toList());
        } catch (IOException x) {
            System.err.println("in catch read operation" + x);
        }
        return allDrivers;
    }

    public List<DriverBean> findByDate(String createdAfterDate) {

        ArrayList<DriverBean> DriversListedAfterGivenDate=new ArrayList<>();
        List<String> listOfDrivers;
        try (InputStream in = Files.newInputStream(p);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            Date createdDate=new SimpleDateFormat("yyyy/mm/dd").parse(createdAfterDate);
            String line = null;
            listOfDrivers = Files.lines(p.toAbsolutePath())
                    .map(t -> t.trim())
                    .filter(t -> !t.isEmpty()).collect(Collectors.toList());

            for (int i = 0; i < listOfDrivers.size(); i++) {
                String eachDriverDataString = listOfDrivers.get(i);
                String[] modelValues= eachDriverDataString.split(",");
                DriverBean bean= new DriverBean();
                for (String value : modelValues) {
                    String[] s = value.split("=");
                    String fieldName = s[0];
                    String fieldValue = s[1];
                    /*DriversListedAfterGivenDate=
                            listOfDrivers.stream()
                                    .filter(fieldName.equalsIgnoreCase("creationDate"))
                    .collect(Collectors.toList())*/;

                /* if (fieldName.equalsIgnoreCase("creationDate")){
                  boolean dateComparisonValue=compareDate(fieldValue,createdDate);
                  if(dateComparisonValue==true){

                  }
                 }*/
                   // BeanUtils.copyProperty(bean,fieldName,fieldValue);
                   // BeanUtils.setProperty(bean,fieldName,fieldValue);

                    //DriversListedAfterGivenDate.add(bean);
                }
                //System.out.println("printing DriversListedAfterGivenDate" +bean.toString());
            }
//            for(int j=0;j<DriversListedAfterGivenDate.size();j++){
//                System.out.println("printing DriversListedAfterGivenDate" +DriversListedAfterGivenDate.get(j));
//            }



        } catch (IOException x) {
            System.err.println("in catch read operation" + x);
        }  /*catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }*/ catch (ParseException e) {
            e.printStackTrace();
        }
       /* try (Stream<String> stream = Files.lines(Paths.get(String.valueOf(p)))) {
            stream.filter(s -> s.endsWith("/"))
                    .forEach(System.out::println);
            System.out.println("in find by date");
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return  DriversListedAfterGivenDate;
    }

    private boolean compareDate(String fieldValue, Date date1) {

        try {
            Date fileDate=new SimpleDateFormat("yyyy/mm/dd").parse(fieldValue);
            SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
            if(date1.after(fileDate)|| date1.equals(fileDate))
                return true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*public boolean isValidDateOfBirthFormat(String dateOfBirth) {
        String format="YYYY-MM-DD";
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(dateOfBirth);
            if (!dateOfBirth.equals(sdf.format(date))) {
                throw new Exception();
            }
        } catch (Exception ex) {
            System.err.println("the date format should be in format YYYY-MM-DD" +ex);
       //     throw new Exception(ex);
        }
      return true;
    }*/


}
