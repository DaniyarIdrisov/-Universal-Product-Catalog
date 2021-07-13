package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.converters;


import org.springframework.core.convert.converter.Converter;

public class StringToDoubleConverter implements Converter<String, Double> {

    @Override
    public Double convert(String string) {
        Double price;
        string = string.replace(',', '.');
        try {
            price = Double.parseDouble(string);
        } catch(NumberFormatException e){
            price = null;
        }
        return price;
    }

}
