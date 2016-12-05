package org.horaapps.leafpic.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.view.IconicsImageView;

import org.horaapps.leafpic.BuildConfig;
import org.horaapps.leafpic.R;
import org.horaapps.leafpic.activities.base.ThemedActivity;
import org.horaapps.leafpic.util.CustomTabService;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Jibo on 02/03/2016.
 */
public class AboutActivity extends ThemedActivity {

    private Toolbar toolbar;
    private CustomTabService cts;
    private ScrollView scr;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        scr = (ScrollView)findViewById(R.id.aboutAct_scrollView);

        cts = new CustomTabService(AboutActivity.this,getPrimaryColor());

        initUi();
        setUpActions();
    }

    private void setUpActions() {
        //GitHub
        findViewById(R.id.ll_about_support_github).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cts.launchUrl("https://github.com/HoraApps/LeafPic");
            }
        });
        ///Report bug
        findViewById(R.id.ll_about_report_bug).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cts.launchUrl("https://github.com/HoraApps/LeafPic/issues");
            }
        });
        //Crowdin
        findViewById(R.id.ll_about_support_translate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cts.launchUrl("https://crowdin.com/project/leafpic");}
        });

        //Donate
        findViewById(R.id.ll_about_support_donate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this, DonateActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //License
        findViewById(R.id.ll_about_license).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cts.launchUrl("https://github.com/HoraApps/LeafPic/blob/master/LICENSE");}
        });

        /*** Donald Shtjefni ***/
        findViewById(R.id.about_author_donald_googleplus_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cts.launchUrl("https://plus.google.com/103359244653769120543/about");}
        });
        findViewById(R.id.about_author_donald_github_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cts.launchUrl("https://github.com/DNLDsht");}
        });
        findViewById(R.id.about_author_donald_mail_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail("dnld.sht@gmail.com");
            }
        });

        /*** Gilbert Ndresaj ***/
        findViewById(R.id.about_author_gilbert_googleplus_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cts.launchUrl("https://plus.google.com/118430643662868782426/about");}
        });
        findViewById(R.id.about_author_gilbert_github_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { cts.launchUrl("https://github.com/Mow3l");}
        });
        findViewById(R.id.about_author_gilbert_mail_item).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail("jibo95@gmail.com");
            }
        });


        /*** SPECIAL THANKS ***/
        /*** Patryk Goworowski ***/
        ((TextView) findViewById(R.id.about_patryk_goworowski_item_sub)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) findViewById(R.id.about_patryk_goworowski_item_sub)).setLinkTextColor(getAccentColor());
    }

    private void mail(String mail) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"+mail));
        try {
            startActivity(intent);
        } catch (Exception e){
            Toast.makeText(AboutActivity.this, getString(R.string.send_mail_error), Toast.LENGTH_SHORT).show();
        }
    }

    private void initUi() {
        setSupportActionBar(toolbar);

        Glide.with(this)
                .load(R.drawable.donald_header)
                //.placeholder(getPlaceHolder())
                .priority(Priority.HIGH)
                .animate(R.anim.fade_in)
                .into((ImageView) findViewById(R.id.donald_header_img));
        Glide.with(this)
                .load(R.drawable.donald_profile)
                .priority(Priority.HIGH)
                //.error(new IconicsDrawable(this, "gmd-person").sizeDp(90).color(getIconColor()).paddingDp(24))
                .animate(R.anim.fade_in)
                .into((CircleImageView) findViewById(R.id.donald_profile_img));

        Glide.with(this)
                .load(R.drawable.gilbert_header)
                .priority(Priority.HIGH)
                //.placeholder(getPlaceHolder())
                .animate(R.anim.fade_in)
                .into((ImageView) findViewById(R.id.gilbert_header_img));
        Glide.with(this)
                .load(R.drawable.gilbert_profile)
                .priority(Priority.HIGH)
                //.error(new IconicsDrawable(this, "gmd-person").sizeDp(90).color(getIconColor()).paddingDp(24))
                .animate(R.anim.fade_in)
                .into((CircleImageView) findViewById(R.id.gilbert_profile_img));

        ((TextView) findViewById(R.id.about_version_item_sub)).setText(BuildConfig.VERSION_NAME);
    }

    @Override
    public void updateUiElements() {
        toolbar.setBackgroundColor(getPrimaryColor());
        toolbar.setNavigationIcon(getToolbarIcon(GoogleMaterial.Icon.gmd_arrow_back));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        setScrollViewColor(scr);
        setStatusBarColor();
        setNavBarColor();
        setRecentApp(getString(R.string.about));

        /**** Title Cards ***/
        int color = getAccentColor();
        ((TextView) findViewById(R.id.about_app_title)).setTextColor(color);
        ((TextView) findViewById(R.id.about_special_thanks_title)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_title)).setTextColor(color);
        ((TextView) findViewById(R.id.about_special_thanks_title)).setTextColor(color);
        ((TextView) findViewById(R.id.about_author_donald_mail_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_author_gilbert_mail_item)).setTextColor(color);

        /***** Donald Card *****/
        ((CircleImageView) findViewById(R.id.donald_profile_img)).setBorderColor(getInvertedBackgroundColor());
        ((CardView) findViewById(R.id.about_donald_card)).setCardBackgroundColor(getCardBackgroundColor());
        ((TextView) findViewById(R.id.donald_shtjefni)).setTextColor(getTextColor());
        ((TextView) findViewById(R.id.donald_description)).setTextColor(getSubTextColor());
        color = getAccentColor();
        ((TextView) findViewById(R.id.about_author_donald_mail_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_author_donald_googleplus_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_author_donald_github_item)).setTextColor(color);

        /***** Gilbert Card *****/
        ((CircleImageView) findViewById(R.id.gilbert_profile_img)).setBorderColor(getInvertedBackgroundColor());
        ((CardView) findViewById(R.id.about_gilbert_card)).setCardBackgroundColor(getCardBackgroundColor());
        ((TextView) findViewById(R.id.gilbert_ndresaj)).setTextColor(getTextColor());
        ((TextView) findViewById(R.id.gilbert_description)).setTextColor(getSubTextColor());
        color = getAccentColor();
        ((TextView) findViewById(R.id.about_author_gilbert_mail_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_author_gilbert_googleplus_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_author_gilbert_github_item)).setTextColor(color);


        findViewById(R.id.about_background).setBackgroundColor(getBackgroundColor());

        /** Cards **/
        color = getCardBackgroundColor();
        ((CardView) findViewById(R.id.about_app_card)).setCardBackgroundColor(color);
        ((CardView) findViewById(R.id.about_special_thanks_card)).setCardBackgroundColor(color);
        ((CardView) findViewById(R.id.about_support_card)).setCardBackgroundColor(color);

        /** Icons **/
        color = getIconColor();

        //ABOUT SUPPORT
        ((IconicsImageView) findViewById(R.id.about_support_translate_icon)).setColor(color);
        ((IconicsImageView) findViewById(R.id.about_support_rate_icon)).setColor(color);
        ((IconicsImageView) findViewById(R.id.about_support_github_icon)).setColor(color);
        ((IconicsImageView) findViewById(R.id.about_support_report_bug_icon)).setColor(color);
        ((IconicsImageView) findViewById(R.id.about_support_donate_icon)).setColor(color);
        ((IconicsImageView) findViewById(R.id.about_license_icon)).setColor(color);

        /** TextViews **/
        color = getTextColor();
        ((TextView) findViewById(R.id.about_app_light_description)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_rate_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_translate_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_github_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_report_bug_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_donate_item)).setTextColor(color);
        ((TextView) findViewById(R.id.about_license_item)).setTextColor(color);

        /** Sub Text Views**/
        color = getSubTextColor();
        ((TextView) findViewById(R.id.about_version_item_sub)).setTextColor(color);
        ((TextView) findViewById(R.id.about_patryk_goworowski_item_sub)).setTextColor(color);
        ((TextView) findViewById(R.id.about_community_members_sub)).setTextColor(color);
        ((TextView) findViewById(R.id.about_community_you_sub)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_rate_item_sub)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_translate_item_sub)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_github_item_sub)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_report_bug_sub)).setTextColor(color);
        ((TextView) findViewById(R.id.about_support_donate_item_sub)).setTextColor(color);
        ((TextView) findViewById(R.id.donald_shtjefni_role)).setTextColor(color);
        ((TextView) findViewById(R.id.gilbert_ndresaj_role)).setTextColor(color);
        ((TextView) findViewById(R.id.about_license_item_sub)).setTextColor(color);
    }
}