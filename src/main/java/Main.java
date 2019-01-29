
public class Main {
  //
    public static void main(String[] args) {
      String parameter="label";
      AccomodationItemURL accomodationURL = new AccomodationItemURL("https://www.booking.com/hotel/me/villa-maya-budva3.html?aid=304142;label=gen173nr-1FCAEoggI46AdIM1gEaCWIAQGYASG4AQbIAQzYAQHoAQH4AQuIAgGoAgM;sid=af83899a5732d59a3d1a06a9b2e59a83&from_list=1;group_rooms=1;group_adults=2;group_children=0;;label=gen173nr-1FCAEoggI46AdIM1gEaCWIAQGYASG4AQbIAQzYAQHoAQH4AQuIAgGoAgM");
      AccomodationItemURL accomodationItemURL = new AccomodationItemURL("https://www.booking.com/hotel/me/villa-maya-budva3.ru.html?aid=304142;label=gen173nr-1FCAEoggI46AdIM1gEaCWIAQGYASG4AQbIAQzYAQHoAQH4AQyIAgGoAgM;sid=458a43461e02dae4a9253f87766b975f;age=12;age=12;age=12;age=12;all_sr_blocks=172018101_127290341_6_2_0;checkin=2019-03-24;checkout=2019-04-03;dest_id=242;dest_type=country;dist=0;group_adults=2;group_children=4;hapos=1;highlighted_blocks=172018101_127290341_6_2_0;hpos=1;no_rooms=5;req_adults=2;req_age=12;req_age=12;req_age=12;req_age=12;req_children=4;room1=A%2CA%2C12%2C12%2C12%2C12;sb_price_type=total;sr_order=popularity;srepoch=1548751317;srpvid=45d83d2add6d0001;type=total;ucfs=1&");
      System.out.println(accomodationURL.getHeader());
      System.out.println(accomodationItemURL.getHeader());
    }
}
