package ru.gb.timesheet.example;

//Класс, который отдает текущий НДС
public class TaxResolver {

    public double getCurrentTax(){
        //идём в ЦБ по НТТР, получаем текущий НДС
        return 0.3;
    }

}
