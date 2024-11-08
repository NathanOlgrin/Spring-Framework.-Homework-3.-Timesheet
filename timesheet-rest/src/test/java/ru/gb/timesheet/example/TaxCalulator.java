package ru.gb.timesheet.example;

public class TaxCalulator {

    private final TaxResolver taxResolver;

    public TaxCalulator(TaxResolver taxResolver) {
        this.taxResolver = taxResolver;
    }

    //Вычисляем стоимость товара с учетом НДС
    public double getPriceWithTax(double price){
        double currentTax = taxResolver.getCurrentTax();
        return price + price *  currentTax;
    }

}