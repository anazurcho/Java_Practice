package ana.zurabashvili.covidpackage.services;

import ana.zurabashvili.covidpackage.model.Covid;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebService
public class CovidService {

    List<Covid> covidCountryStatistic=new ArrayList<Covid>(Arrays.asList(
        new Covid("USA",819321,45356,83008),
        new Covid("Spain",208389,21717,85915),
        new Covid("Italy",183957,24648,39181),
        new Covid("France",158050,20796,39181),
        new Covid("Germany",148925,5117,99400),
        new Covid("UK",133495,18100,9181),
        new Covid("Turkey",95591,2259,14918)
    ));

    @WebMethod
    public List<Covid> getStatistic(){
        return covidCountryStatistic;
    }

    @WebMethod
    public Covid getCountryStatistic(String country){
        for (Covid covid: covidCountryStatistic){
            if(covid.getCountry().equals(country)){ return covid; }
        }
        return null;
    }

    @WebMethod
    public int TotalConfirmed(){
        int result = 0;
        for (Covid covid: covidCountryStatistic){result += covid.getConfirmed();}
        return result;
    }

    @WebMethod
    public int TotalDeathget(){
        int result = 0;
        for (Covid covid: covidCountryStatistic){result += covid.getDeath(); }
        return result;
    }

    @WebMethod
    public int TotalRecoveryget(){
        int result = 0;
        for (Covid covid: covidCountryStatistic){result += covid.getRecovered(); }
        return result;
    }

}
