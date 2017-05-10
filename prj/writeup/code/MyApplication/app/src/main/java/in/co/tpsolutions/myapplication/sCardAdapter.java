package in.co.tpsolutions.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anurag on 3/29/2016.
 */
public class sCardAdapter extends RecyclerView.Adapter<sCardAdapter.ViewHolder> {

    private Context context;

    Bitmap s_image, s_image1, s_image2, s_image3;

    //List to store all superheroes
    List<sResults> s_results;

    //Constructor of this class

    public sCardAdapter(List<sResults> sResultsList, FragmentActivity activity) {
        super();
        //Getting all superheroes
        this.s_results = s_results;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.suggestions_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        //Getting the particular item from the list
        sResults s_result =  s_results.get(position);

        //Showing data on the views
        // Getting Data
        // Control Variables
        String f1_post = "0";//bm_result.getbm_f1_post();
        String f2_roommate = "1";// bm_result.getbm_f2_roommate();
        String f3_roomo_rent = "1";//bm_result.getbm_f3_roomo_rent();

        //Remaining variables 36
        String username = s_result.gets_Username();
        String email = s_result.gets_Email();
        String gender = s_result.gets_gender();
        String age = s_result.gets_age();
        String phonenumber = s_result.gets_PhoneNo();
        String occupation = s_result.gets_occupation();
        String city = s_result.gets_city();
        String country = s_result.gets_country();
        String nationality = s_result.gets_nationality();
        String m_stat = s_result.gets_m_stat();
        String type_of_house = s_result.gets_type_of_house();
        String no_of_rooms = s_result.gets_no_of_rooms();
        String rentbudget = s_result.gets_rentbudget();
        String owner_rent = s_result.gets_owner_rent();
        String no_of_roommates = s_result.gets_no_of_roommates();
        String occupancy = s_result.gets_occupancy();
        String cooking_skills = s_result.gets_cooking_skills();
        String owl = s_result.gets_owl();
        String message = s_result.gets_message();
        String alcoholic = s_result.gets_alcoholic();
        String smoker = s_result.gets_smoker();
        String address = s_result.gets_address();
        String rent = s_result.gets_rent();
        String tv = s_result.gets_tv();
        String ac = s_result.gets_ac();
        String fridge = s_result.gets_fridge();
        String washing_machine = s_result.gets_washing_machine();
        String internet = s_result.gets_internet();
        String geaser = s_result.gets_geaser();
        String image = s_result.gets_image();
        String food = s_result.gets_food();
        String p_image1 = s_result.getPImage1();
        String p_image2 = s_result.gets_p_image2();
        String p_image3 = s_result.gets_p_image3();
        String zero = "0";
        String yes="Yes";


        if (f1_post.equals(zero)) {
            // Your Post for Need a Room
            // Can't Pass Required Fragments
            //Display it Here
            // Concatenate "," for City
            // Concatenate "Rooms" for No of Rooms
            // Concatenate "persons" for No of roomates
            holder.s_NameData.setText(username);
            holder.s_EmailData.setText(email);
            holder.s_GenderData.setText(gender);
            holder.s_PhnoData.setText(phonenumber);
            holder.s_CityData.setText(city + ",");
            holder.s_Type_of_house_data.setText(type_of_house);
            holder.s_CountryData.setText(country);
            holder.s_NationalityData.setText(nationality);
            holder.s_RentTypeData.setText("Rs." + rentbudget);
            holder.s_AgeData.setText(age);
            holder.s_m_statData.setText(m_stat);
            holder.s_NoOfRoomsData.setText(no_of_rooms + "Rooms");
            holder.s_NoOfRoomatesData.setText(no_of_roommates + "Persons");
            holder.s_FoodHabitsData.setText(food);
            holder.s_MessageData.setText(message);
            holder.s_OccupancyData.setText(occupancy);
            holder.s_URA_Data.setText(owl);
            holder.s_OccupationData.setText(occupation);
            holder.s_CookingSkillsData.setText(cooking_skills);
            holder.s_AddressData.setText(address);

            // Image Views
            if(image != null){
                s_image = decodeBase64_postImages(image);
                holder.s_ImageImageView.setImageBitmap(s_image);
            }

            /*if (smoker.equals(yes)){
                holder.s_smoking_ImageView.setImageResource(R.drawable.smk);
            }
            else{
                holder.s_smoking_ImageView.setImageResource(R.drawable.nosmking);
            }

            if (alcoholic.equals(yes)){
                holder.s_alcho_ImageView.setImageResource(R.drawable.alco);
            }
            else{
                holder.s_alcho_ImageView.setImageResource(R.drawable.noal);
            }*/
        }

        if (f2_roommate.equals(zero)){
            // Your Post for Have a Room by Owner
            // Can't Pass Required Fragments
            //Display it Here

            holder.s_NameData.setText(username);
            holder.s_EmailData.setText(email);
            holder.s_GenderData.setText(gender);
            holder.s_PhnoData.setText(phonenumber);
            holder.s_CityData.setText(city + ",");
            holder.s_Type_of_house_data.setText(type_of_house);
            holder.s_CountryData.setText(country);
            holder.s_NationalityData.setText(nationality);
            holder.s_RentTypeData.setText("Rs." + owner_rent);
            holder.s_AgeData.setText(age);
            holder.s_m_statData.setText(m_stat);
            holder.s_NoOfRoomsData.setText(no_of_rooms + "Rooms");
            holder.s_NoOfRoomatesData.setText(no_of_roommates + "Persons");
            holder.s_FoodHabitsData.setText(food);
            holder.s_MessageData.setText(message);
            holder.s_OccupancyData.setText(occupancy);
            holder.s_URA_Data.setText(owl);
            holder.s_OccupationData.setText(occupation);
            holder.s_CookingSkillsData.setText(cooking_skills);
            holder.s_AddressData.setText(address);

            // Image Views
            if(image != null){
                s_image = decodeBase64_postImages(image);
                holder.s_ImageImageView.setImageBitmap(s_image);
            }

            s_image1 = decodeBase64_postImages(p_image1);
            holder.s_imageView1.setImageBitmap(s_image1);

            s_image2 = decodeBase64_postImages(p_image2);
            holder.s_imageView2.setImageBitmap(s_image2);

            s_image3 = decodeBase64_postImages(p_image3);
            holder.s_imageView3.setImageBitmap(s_image3);

            if (smoker.equals(yes)){
                holder.s_smoking_ImageView.setImageResource(R.drawable.smk);
            }
            else{
                holder.s_smoking_ImageView.setImageResource(R.drawable.nosmking);
            }

            if (alcoholic.equals(yes)){
                holder.s_alcho_ImageView.setImageResource(R.drawable.alco);
            }
            else{
                holder.s_alcho_ImageView.setImageResource(R.drawable.noal);
            }

            if (tv.equals(yes)){
                holder.s_tv_ImageView.setImageResource(R.drawable.tv);
            }

            if (washing_machine.equals(yes)){
                holder.s_wm_ImageView.setImageResource(R.drawable.wm);
            }

            if (ac.equals(yes)){
                holder.s_ac_ImageView.setImageResource(R.drawable.ac);
            }

            if (fridge.equals(yes)){
                holder.s_fridge_ImageView.setImageResource(R.drawable.fr);
            }

            if (geaser.equals(yes)){
                holder.s_geaser_ImageView.setImageResource(R.drawable.geaser);
            }

            if (internet.equals(yes)){
                holder.s_internet_ImageView.setImageResource(R.drawable.wi);
            }
        }

        if (f3_roomo_rent.equals(zero)){
            // Your Post for Need a Roommate
            // Can't Pass Required Fragments
            //Display it Here

            holder.s_NameData.setText(username);
            holder.s_EmailData.setText(email);
            holder.s_GenderData.setText(gender);
            holder.s_PhnoData.setText(phonenumber);
            holder.s_CityData.setText(city + ",");
            holder.s_Type_of_house_data.setText(type_of_house);
            holder.s_CountryData.setText(country);
            holder.s_NationalityData.setText(nationality);
            holder.s_RentTypeData.setText("Rs." + rent);
            holder.s_AgeData.setText(age);
            holder.s_m_statData.setText(m_stat);
            holder.s_NoOfRoomsData.setText(no_of_rooms + "Rooms");
            holder.s_NoOfRoomatesData.setText(no_of_roommates + "Persons");
            holder.s_FoodHabitsData.setText(food);
            holder.s_MessageData.setText(message);
            holder.s_OccupancyData.setText(occupancy);
            holder.s_URA_Data.setText(owl);
            holder.s_OccupationData.setText(occupation);
            holder.s_CookingSkillsData.setText(cooking_skills);
            holder.s_AddressData.setText(address);

            // Image Views
            if(image != null){
                s_image = decodeBase64_postImages(image);
                holder.s_ImageImageView.setImageBitmap(s_image);
            }

            s_image1 = decodeBase64_postImages(p_image1);
            holder.s_imageView1.setImageBitmap(s_image1);

            s_image2 = decodeBase64_postImages(p_image2);
            holder.s_imageView2.setImageBitmap(s_image2);

            s_image3 = decodeBase64_postImages(p_image3);
            holder.s_imageView3.setImageBitmap(s_image3);

            if (smoker.equals(yes)){
                holder.s_smoking_ImageView.setImageResource(R.drawable.smk);
            }
            else{
                holder.s_smoking_ImageView.setImageResource(R.drawable.nosmking);
            }

            if (alcoholic.equals(yes)){
                holder.s_alcho_ImageView.setImageResource(R.drawable.alco);
            }
            else{
                holder.s_alcho_ImageView.setImageResource(R.drawable.noal);
            }

            if (tv.equals(yes)){
                holder.s_tv_ImageView.setImageResource(R.drawable.tv);
            }

            if (washing_machine.equals(yes)){
                holder.s_wm_ImageView.setImageResource(R.drawable.wm);
            }

            if (ac.equals(yes)){
                holder.s_ac_ImageView.setImageResource(R.drawable.ac);
            }

            if (fridge.equals(yes)){
                holder.s_fridge_ImageView.setImageResource(R.drawable.fr);
            }

            if (geaser.equals(yes)){
                holder.s_geaser_ImageView.setImageResource(R.drawable.geaser);
            }

            if (internet.equals(yes)){
                holder.s_internet_ImageView.setImageResource(R.drawable.wi);
            }
        }
    }

    private Bitmap decodeBase64_postImages(String img12) {
        byte [] decodedBytes = Base64.decode(img12, 0);
        Bitmap imageView11 = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
        return imageView11;
    }

    @Override
    public int getItemCount() {
        //return s_results.size();
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        //Views
        public ImageView p_img1;

        // Best Match Results
        // Text Views Of Your Post
        TextView s_NameData, s_EmailData, s_GenderData, s_PhnoData, s_CityData, s_Type_of_house_data, s_CountryData, s_NationalityData,
                s_RentTypeData, s_AgeData, s_m_statData, s_NoOfRoomsData, s_NoOfRoomatesData,
                s_FoodHabitsData, s_MessageData, s_OccupancyData, s_URA_Data, s_OccupationData, s_CookingSkillsData, s_AddressData;


        //Image View for Best Match Results
        ImageView s_imageView1, s_imageView2, s_imageView3, s_tv_ImageView, s_wm_ImageView, s_fridge_ImageView, s_internet_ImageView,
                s_geaser_ImageView, s_ac_ImageView, s_ImageImageView, s_smoking_ImageView, s_alcho_ImageView;

        //Initializing Views
        public ViewHolder(View itemView) {
            super(itemView);

            // Text Views
            s_NameData = (TextView) itemView.findViewById(R.id.s_tx_name);
            s_EmailData = (TextView) itemView.findViewById(R.id.s_tx_email);
            s_GenderData = (TextView) itemView.findViewById(R.id.s_tx_Gender);
            s_PhnoData = (TextView) itemView.findViewById(R.id.s_tx_phoneNo);
            s_CityData = (TextView) itemView.findViewById(R.id.s_tx_City);
            s_Type_of_house_data = (TextView) itemView.findViewById(R.id.s_tx_Type_Of_House);
            s_CountryData = (TextView) itemView.findViewById(R.id.s_tx_Country);
            s_NationalityData = (TextView) itemView.findViewById(R.id.s_tx_Nationality);
            s_RentTypeData = (TextView) itemView.findViewById(R.id.s_tx_Rent);
            s_AgeData = (TextView) itemView.findViewById(R.id.s_tx_Age);
            s_m_statData = (TextView) itemView.findViewById(R.id.s_tx_m_stat);
            s_NoOfRoomsData = (TextView) itemView.findViewById(R.id.s_tx_No_of_rooms);
            s_NoOfRoomatesData = (TextView) itemView.findViewById(R.id.s_tx_No_of_roommates);
            s_FoodHabitsData = (TextView) itemView.findViewById(R.id.s_tx_Food);
            s_MessageData = (TextView) itemView.findViewById(R.id.s_tx_Message);
            s_OccupancyData = (TextView) itemView.findViewById(R.id.s_tx_Occupancy);
            s_URA_Data = (TextView) itemView.findViewById(R.id.s_tx_URA);
            s_OccupationData = (TextView) itemView.findViewById(R.id.s_tx_Occupation);
            s_CookingSkillsData = (TextView) itemView.findViewById(R.id.s_tx_Cooking);
            s_AddressData = (TextView) itemView.findViewById(R.id.s_tx_Address);

            // Image Views
            s_ImageImageView = (ImageView) itemView.findViewById(R.id.s_imageView);
            s_imageView1 = (ImageView) itemView.findViewById(R.id.s_p_imageView1);
            s_imageView2 = (ImageView) itemView.findViewById(R.id.s_p_imageView2);
            s_imageView3 = (ImageView) itemView.findViewById(R.id.s_p_imageView3);
            s_tv_ImageView = (ImageView) itemView.findViewById(R.id.s_tv_imageView);
            s_wm_ImageView = (ImageView) itemView.findViewById(R.id.s_wm_imageView);
            s_fridge_ImageView = (ImageView) itemView.findViewById(R.id.s_fridge_imageView);
            s_internet_ImageView = (ImageView) itemView.findViewById(R.id.s_internet_imageView);
            s_geaser_ImageView = (ImageView) itemView.findViewById(R.id.s_geaser_imageView);
            s_ac_ImageView = (ImageView) itemView.findViewById(R.id.s_ac_imageView);
            s_smoking_ImageView = (ImageView) itemView.findViewById(R.id.s_smoking_imageView);
            s_alcho_ImageView = (ImageView) itemView.findViewById(R.id.s_alcho_imageView);
        }
    }
    
}
