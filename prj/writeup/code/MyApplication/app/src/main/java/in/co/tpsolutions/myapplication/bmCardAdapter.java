package in.co.tpsolutions.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Anurag on 3/26/2016.
 */
    public class bmCardAdapter extends RecyclerView.Adapter<bmCardAdapter.ViewHolder> {

        private Context context;

        Bitmap bm_image, bm_image1, bm_image2, bm_image3;

        //List to store all superheroes
        List<bmResults> bm_results;

        //Constructor of this class
        public bmCardAdapter(List<bmResults> bm_results, Context context){
            super();
            //Getting all superheroes
            this.bm_results = bm_results;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.best_matches_layout, parent, false);
            ViewHolder viewHolder = new ViewHolder(v);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            //Getting the particular item from the list
            bmResults bm_result =  bm_results.get(position);

            //Showing data on the views
            // Getting Data
            // Control Variables
            String f1_post = "0";//bm_result.getbm_f1_post();
            String f2_roommate = "1";// bm_result.getbm_f2_roommate();
            String f3_roomo_rent = "1";//bm_result.getbm_f3_roomo_rent();

            //Remaining variables
            String username = bm_result.getbm_Username();
            String email = bm_result.getbm_Email();
            String gender = bm_result.getBM_gender();
            String age = bm_result.getBM_age();
            String phonenumber = bm_result.getBM_PhoneNo();
            String occupation = bm_result.getBM_occupation();
            String city = bm_result.getBM_city();
            String country = bm_result.getBM_country();
            String nationality = bm_result.getBM_nationality();
            String m_stat = bm_result.getBM_m_stat();
            String type_of_house = bm_result.getBM_type_of_house();
            String no_of_rooms = bm_result.getBM_no_of_rooms();
            String rentbudget = bm_result.getBM_rentbudget();
            String owner_rent = bm_result.getBM_owner_rent();
            String no_of_roommates = bm_result.getBM_no_of_roommates();
            String occupancy = bm_result.getBM_occupancy();
            String cooking_skills = bm_result.getBM_cooking_skills();
            String owl = bm_result.getBM_owl();
            String message = bm_result.getBM_message();
            String alcoholic = bm_result.getBM_alcoholic();
            String smoker = bm_result.getBM_smoker();
            String address = bm_result.getBM_address();
            String rent = bm_result.getBM_rent();
            String tv = bm_result.getBM_tv();
            String ac = bm_result.getBM_ac();
            String fridge = bm_result.getBM_fridge();
            String washing_machine = bm_result.getBM_washing_machine();
            String internet = bm_result.getBM_internet();
            String geaser = bm_result.getBM_geaser();
            String image = bm_result.getBM_image();
            String food = bm_result.getBM_food();
            String p_image1 = bm_result.getPImage1();
            String p_image2 = bm_result.getBM_p_image2();
            String p_image3 = bm_result.getBM_p_image3();
            String zero = "0";
            String yes="Yes";


            if (f1_post.equals(zero)) {
                // Your Post for Need a Room
                // Can't Pass Required Fragments
                //Display it Here
                // Concatenate "," for City
                // Concatenate "Rooms" for No of Rooms
                // Concatenate "persons" for No of roomates
                //holder.bm_NameData.setText(username);
                holder.bm_EmailData.setText(email);
                holder.bm_GenderData.setText(gender);
                holder.bm_PhnoData.setText(phonenumber);
                holder.bm_CityData.setText(city + ",");
                holder.bm_Type_of_house_data.setText(type_of_house);
                holder.bm_CountryData.setText(country);
                holder.bm_NationalityData.setText(nationality);
                holder.bm_RentTypeData.setText("Rs." + rentbudget);
                holder.bm_AgeData.setText(age);
                holder.bm_m_statData.setText(m_stat);
                holder.bm_NoOfRoomsData.setText(no_of_rooms + "Rooms");
                holder.bm_NoOfRoomatesData.setText(no_of_roommates + "Persons");
                holder.bm_FoodHabitsData.setText(food);
                holder.bm_MessageData.setText(message);
                holder.bm_OccupancyData.setText(occupancy);
                holder.bm_URA_Data.setText(owl);
                holder.bm_OccupationData.setText(occupation);
                holder.bm_CookingSkillsData.setText(cooking_skills);
                holder.bm_AddressData.setText(address);

                bm_image = decodeBase64_postImages(image);
                holder.bm_ImageImageView.setImageBitmap(bm_image);

                // Image Views
                bm_image = decodeBase64_postImages(image);
                holder.bm_ImageImageView.setImageBitmap(bm_image);

                bm_image1 = decodeBase64_postImages(p_image1);
                holder.bm_imageView1.setImageBitmap(bm_image1);

                bm_image2 = decodeBase64_postImages(p_image2);
                holder.bm_imageView2.setImageBitmap(bm_image2);

                bm_image3 = decodeBase64_postImages(p_image3);
                holder.bm_imageView3.setImageBitmap(bm_image3);


                // holder.bm_smoking_ImageView.setImageResource(R.drawable.smk);


                holder.bm_smoking_ImageView.setImageResource(R.drawable.nosmking);



                    //holder.bm_alcho_ImageView.setImageResource(R.drawable.alco);


                    holder.bm_alcho_ImageView.setImageResource(R.drawable.noal);


                    holder.bm_tv_ImageView.setImageResource(R.drawable.tv);

                    holder.bm_wm_ImageView.setImageResource(R.drawable.wm);
                    holder.bm_ac_ImageView.setImageResource(R.drawable.ac);

                    holder.bm_fridge_ImageView.setImageResource(R.drawable.fr);

                    holder.bm_geaser_ImageView.setImageResource(R.drawable.geaser);

                    holder.bm_internet_ImageView.setImageResource(R.drawable.wi);


            }

            if (f2_roommate.equals(zero)){
                // Your Post for Have a Room by Owner
                // Can't Pass Required Fragments
                //Display it Here

                holder.bm_NameData.setText(username);
                holder.bm_EmailData.setText(email);
                holder.bm_GenderData.setText(gender);
                holder.bm_PhnoData.setText(phonenumber);
                holder.bm_CityData.setText(city + ",");
                holder.bm_Type_of_house_data.setText(type_of_house);
                holder.bm_CountryData.setText(country);
                holder.bm_NationalityData.setText(nationality);
                holder.bm_RentTypeData.setText("Rs." + owner_rent);
                holder.bm_AgeData.setText(age);
                holder.bm_m_statData.setText(m_stat);
                holder.bm_NoOfRoomsData.setText(no_of_rooms + "Rooms");
                holder.bm_NoOfRoomatesData.setText(no_of_roommates + "Persons");
                holder.bm_FoodHabitsData.setText(food);
                holder.bm_MessageData.setText(message);
                holder.bm_OccupancyData.setText(occupancy);
                holder.bm_URA_Data.setText(owl);
                holder.bm_OccupationData.setText(occupation);
                holder.bm_CookingSkillsData.setText(cooking_skills);
                holder.bm_AddressData.setText(address);

                // Image Views
                bm_image = decodeBase64_postImages(image);
                holder.bm_ImageImageView.setImageBitmap(bm_image);

                bm_image1 = decodeBase64_postImages(p_image1);
                holder.bm_imageView1.setImageBitmap(bm_image1);

                bm_image2 = decodeBase64_postImages(p_image2);
                holder.bm_imageView2.setImageBitmap(bm_image2);

                bm_image3 = decodeBase64_postImages(p_image3);
                holder.bm_imageView3.setImageBitmap(bm_image3);

                if (smoker.equals(yes)){
                    holder.bm_smoking_ImageView.setImageResource(R.drawable.smk);
                }
                else{
                    holder.bm_smoking_ImageView.setImageResource(R.drawable.nosmking);
                }

                if (alcoholic.equals(yes)){
                    holder.bm_alcho_ImageView.setImageResource(R.drawable.alco);
                }
                else{
                    holder.bm_alcho_ImageView.setImageResource(R.drawable.noal);
                }

                if (tv.equals(yes)){
                    holder.bm_tv_ImageView.setImageResource(R.drawable.tv);
                }

                if (washing_machine.equals(yes)){
                    holder.bm_wm_ImageView.setImageResource(R.drawable.wm);
                }

                if (ac.equals(yes)){
                    holder.bm_ac_ImageView.setImageResource(R.drawable.ac);
                }

                if (fridge.equals(yes)){
                    holder.bm_fridge_ImageView.setImageResource(R.drawable.fr);
                }

                if (geaser.equals(yes)){
                    holder.bm_geaser_ImageView.setImageResource(R.drawable.geaser);
                }

                if (internet.equals(yes)){
                    holder.bm_internet_ImageView.setImageResource(R.drawable.wi);
                }
            }

            if (f3_roomo_rent.equals(zero)){
                // Your Post for Need a Roommate
                // Can't Pass Required Fragments
                //Display it Here

                holder.bm_NameData.setText(username);
                holder.bm_EmailData.setText(email);
                holder.bm_GenderData.setText(gender);
                holder.bm_PhnoData.setText(phonenumber);
                holder.bm_CityData.setText(city + ",");
                holder.bm_Type_of_house_data.setText(type_of_house);
                holder.bm_CountryData.setText(country);
                holder.bm_NationalityData.setText(nationality);
                holder.bm_RentTypeData.setText("Rs." + rent);
                holder.bm_AgeData.setText(age);
                holder.bm_m_statData.setText(m_stat);
                holder.bm_NoOfRoomsData.setText(no_of_rooms + "Rooms");
                holder.bm_NoOfRoomatesData.setText(no_of_roommates + "Persons");
                holder.bm_FoodHabitsData.setText(food);
                holder.bm_MessageData.setText(message);
                holder.bm_OccupancyData.setText(occupancy);
                holder.bm_URA_Data.setText(owl);
                holder.bm_OccupationData.setText(occupation);
                holder.bm_CookingSkillsData.setText(cooking_skills);
                holder.bm_AddressData.setText(address);

                // Image Views
                bm_image = decodeBase64_postImages(image);
                holder.bm_ImageImageView.setImageBitmap(bm_image);

                bm_image1 = decodeBase64_postImages(p_image1);
                holder.bm_imageView1.setImageBitmap(bm_image1);

                bm_image2 = decodeBase64_postImages(p_image2);
                holder.bm_imageView2.setImageBitmap(bm_image2);

                bm_image3 = decodeBase64_postImages(p_image3);
                holder.bm_imageView3.setImageBitmap(bm_image3);


                if (smoker.equals(yes)){
                    holder.bm_smoking_ImageView.setImageResource(R.drawable.smk);
                }
                else{
                    holder.bm_smoking_ImageView.setImageResource(R.drawable.nosmking);
                }

                if (alcoholic.equals(yes)){
                    holder.bm_alcho_ImageView.setImageResource(R.drawable.alco);
                }
                else{
                    holder.bm_alcho_ImageView.setImageResource(R.drawable.noal);
                }

                if (tv.equals(yes)){
                    holder.bm_tv_ImageView.setImageResource(R.drawable.tv);
                }

                if (washing_machine.equals(yes)){
                    holder.bm_wm_ImageView.setImageResource(R.drawable.wm);
                }

                if (ac.equals(yes)){
                    holder.bm_ac_ImageView.setImageResource(R.drawable.ac);
                }

                if (fridge.equals(yes)){
                    holder.bm_fridge_ImageView.setImageResource(R.drawable.fr);
                }

                if (geaser.equals(yes)){
                    holder.bm_geaser_ImageView.setImageResource(R.drawable.geaser);
                }

                if (internet.equals(yes)){
                    holder.bm_internet_ImageView.setImageResource(R.drawable.wi);
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
            return bm_results.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            //Views
            public ImageView p_img1;

            // Best Match Results
            // Text Views Of Your Post
            TextView bm_NameData, bm_EmailData, bm_GenderData, bm_PhnoData, bm_CityData, bm_Type_of_house_data, bm_CountryData, bm_NationalityData,
                    bm_RentTypeData, bm_AgeData, bm_m_statData, bm_NoOfRoomsData, bm_NoOfRoomatesData,
                    bm_FoodHabitsData, bm_MessageData, bm_OccupancyData, bm_URA_Data, bm_OccupationData, bm_CookingSkillsData, bm_AddressData;


            //Image View for Best Match Results
            ImageView bm_imageView1, bm_imageView2, bm_imageView3, bm_tv_ImageView, bm_wm_ImageView, bm_fridge_ImageView, bm_internet_ImageView,
                    bm_geaser_ImageView, bm_ac_ImageView, bm_ImageImageView, bm_smoking_ImageView, bm_alcho_ImageView;

            //Initializing Views
            public ViewHolder(View itemView) {
                super(itemView);

               // Text Views
                bm_NameData = (TextView) itemView.findViewById(R.id.bm_tx_name);
                bm_EmailData = (TextView) itemView.findViewById(R.id.bm_tx_email);
                bm_GenderData = (TextView) itemView.findViewById(R.id.bm_tx_Gender);
                bm_PhnoData = (TextView) itemView.findViewById(R.id.bm_tx_phoneNo);
                bm_CityData = (TextView) itemView.findViewById(R.id.bm_tx_City);
                bm_Type_of_house_data = (TextView) itemView.findViewById(R.id.bm_tx_Type_Of_House);
                bm_CountryData = (TextView) itemView.findViewById(R.id.bm_tx_Country);
                bm_NationalityData = (TextView) itemView.findViewById(R.id.bm_tx_Nationality);
                bm_RentTypeData = (TextView) itemView.findViewById(R.id.bm_tx_Rent);
                bm_AgeData = (TextView) itemView.findViewById(R.id.bm_tx_Age);
                bm_m_statData = (TextView) itemView.findViewById(R.id.bm_tx_m_stat);
                bm_NoOfRoomsData = (TextView) itemView.findViewById(R.id.bm_tx_No_of_rooms);
                bm_NoOfRoomatesData = (TextView) itemView.findViewById(R.id.bm_tx_No_of_roommates);
                bm_FoodHabitsData = (TextView) itemView.findViewById(R.id.bm_tx_Food);
                bm_MessageData = (TextView) itemView.findViewById(R.id.bm_tx_Message);
                bm_OccupancyData = (TextView) itemView.findViewById(R.id.bm_tx_Occupancy);
                bm_URA_Data = (TextView) itemView.findViewById(R.id.bm_tx_URA);
                bm_OccupationData = (TextView) itemView.findViewById(R.id.bm_tx_Occupation);
                bm_CookingSkillsData = (TextView) itemView.findViewById(R.id.bm_tx_Cooking);
                bm_AddressData = (TextView) itemView.findViewById(R.id.bm_tx_Address);

                // Image Views
                bm_ImageImageView = (ImageView) itemView.findViewById(R.id.bm_imageView);
                bm_imageView1 = (ImageView) itemView.findViewById(R.id.bm_p_imageView1);
                bm_imageView2 = (ImageView) itemView.findViewById(R.id.bm_p_imageView2);
                bm_imageView3 = (ImageView) itemView.findViewById(R.id.bm_p_imageView3);
                bm_tv_ImageView = (ImageView) itemView.findViewById(R.id.bm_tv_imageView);
                bm_wm_ImageView = (ImageView) itemView.findViewById(R.id.bm_wm_imageView);
                bm_fridge_ImageView = (ImageView) itemView.findViewById(R.id.bm_fridge_imageView);
                bm_internet_ImageView = (ImageView) itemView.findViewById(R.id.bm_internet_imageView);
                bm_geaser_ImageView = (ImageView) itemView.findViewById(R.id.bm_geaser_imageView);
                bm_ac_ImageView = (ImageView) itemView.findViewById(R.id.bm_ac_imageView);
                bm_smoking_ImageView = (ImageView) itemView.findViewById(R.id.bm_smoking_imageView);
                bm_alcho_ImageView = (ImageView) itemView.findViewById(R.id.bm_alcho_imageView);
            }
        }
    }
