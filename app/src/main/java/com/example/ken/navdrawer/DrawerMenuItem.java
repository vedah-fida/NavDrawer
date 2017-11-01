package com.example.ken.navdrawer;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.Animation;
import com.mindorks.placeholderview.PlaceHolderView;
import com.mindorks.placeholderview.annotations.Animate;
import com.mindorks.placeholderview.annotations.Click;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.LongClick;
import com.mindorks.placeholderview.annotations.NonReusable;
import com.mindorks.placeholderview.annotations.Position;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;

/**
 * Created by ken on 30-Oct-17.
 */
@Layout(R.layout.drawer_item)
public class DrawerMenuItem {

    public static final int DRAWER_MENU_ITEM_UPLOADS = 1;
    public static final int DRAWER_MENU_ITEM_TUTORIALS = 2;
    public static final int DRAWER_MENU_ITEM_REVIEWS = 3;
    public static final int DRAWER_MENU_ITEM_PRIVACY = 4;
    public static final int DRAWER_MENU_ITEM_LOGOUT = 5;

    private int mMenuPosition;
    private Context mContext;
    private DrawerCallBack mCallBack;

    @View(R.id.itemNameTxt)
    private TextView itemNameTxt;

    @View(R.id.itemIcon)
    private ImageView itemIcon;

    public DrawerMenuItem(Context context, int menuPosition) {
        mContext = context;
        mMenuPosition = menuPosition;
    }

    @Resolve
    private void onResolved() {
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_UPLOADS:
                itemNameTxt.setText("My Videos");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_account_circle_black_18dp));
                break;
            case DRAWER_MENU_ITEM_TUTORIALS:
                itemNameTxt.setText("Tutorials");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_compare_arrows_black_18dp));
                break;
            case DRAWER_MENU_ITEM_REVIEWS:
                itemNameTxt.setText("Reviews");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_group_work_black_18dp));
                break;
            case DRAWER_MENU_ITEM_PRIVACY:
                itemNameTxt.setText("Privacy");
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_email_black_18dp));
                break;
            case DRAWER_MENU_ITEM_LOGOUT:
                itemIcon.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_exit_to_app_black_18dp));
                itemNameTxt.setText("Logout");
                break;
        }
    }

    @Click(R.id.mainView)
    private void onMenuItemClick(){
        switch (mMenuPosition){
            case DRAWER_MENU_ITEM_UPLOADS:
                //Toast.makeText(mContext, "Profile", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onProfileMenuSelected();
                break;
            case DRAWER_MENU_ITEM_TUTORIALS:
                //Toast.makeText(mContext, "Requests", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onRequestMenuSelected();
                break;
            case DRAWER_MENU_ITEM_REVIEWS:
                //Toast.makeText(mContext, "Groups", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onGroupsMenuSelected();
                break;
            case DRAWER_MENU_ITEM_PRIVACY:
                //Toast.makeText(mContext, "Messages", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onMessagesMenuSelected();
                break;
            case DRAWER_MENU_ITEM_LOGOUT:
                //Toast.makeText(mContext, "Logout", Toast.LENGTH_SHORT).show();
                if(mCallBack != null)mCallBack.onLogoutMenuSelected();
                break;
        }
    }

    public void setDrawerCallBack(DrawerCallBack callBack) {
        mCallBack = callBack;
    }

    public interface DrawerCallBack{
        void onProfileMenuSelected();
        void onRequestMenuSelected();
        void onGroupsMenuSelected();
        void onMessagesMenuSelected();
        void onNotificationsMenuSelected();
        void onSettingsMenuSelected();
        void onTermsMenuSelected();
        void onLogoutMenuSelected();
    }
}