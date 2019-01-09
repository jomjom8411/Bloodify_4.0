package com.example.dhoui.bloodify;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context) {

        this.context = context;
    }

    //Arrays
    public int[] slide_images = {

            R.drawable.a4,
            R.drawable.a3,
            R.drawable.a2,
            R.drawable.a5,
            R.drawable.a1,
            R.drawable.yo,
            R.drawable.a6

    };

    public String[] slide_headings = {

            "Conseil:1",
            "Conseil:2",
            "Conseil:3",
            "Conseil:4",
            "Conseil:5",
            "Conseil:6",
            "Conseil:7",

    };

    public String[] slide_descs = {

            " Tout antécédent d'injection de produits stupéfiants par voie intraveineuse, même remontant à plusieurs années, constitue une contre-indication permanente au don de sang.",
            "Vous devez peser plus de 50 kg car ce poids minimum garantit votre sécurité.",
            "Par mesure de précaution, le don de sang n’est pas possible actuellement si vous avez reçu une transfusion sanguine ou une greffe. Attention, il ne faut pas confondre transfusion et perfusion",
            "Pour éviter tout risque de carence, vous ne pouvez pas donner si vous êtes enceinte. Et vous devez attendre 6 mois après l’accouchement.",
   "Si vous souffrez d’anémie, vous devez attendre que votre taux d’hémoglobine revienne à la normale pour donner votre sang.",
       "Le don de sang est possible un jour après un détartrage ou le traitement d’une carie et une semaine après une extraction dentaire.",
       "Vous ne pouvez pas donner si vous avez eu une relation sexuelle, même protégée, avec plus d’un partenaire au cours des 4 derniers mois. Cette contre-indication ne s’applique pas aux femmes ayant eu des relations sexuelles uniquement avec des femmes."


    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (RelativeLayout) o;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_layout, container, false);

        ImageView slideImageView = (ImageView) view.findViewById(R.id.slide_image);
        TextView slideHeading = (TextView) view.findViewById(R.id.slide_heading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slide_desc);

        slideImageView.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_descs[position]);

        container.addView(view);

        return view;

    }


    @Override
    public void destroyItem (ViewGroup container, int position, Object object) {

        container.removeView((RelativeLayout)object);
    }
}