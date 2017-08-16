package com.petecc.pro.peteccenforcesystem.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.petecc.pro.peteccenforcesystem.R;

import java.util.List;

/**
 * Created by daiyf on 2016/4/26.
 * recycleView 的适配器
 */
public abstract class MyRecycleViewAdapter<T> extends RecyclerView.Adapter {

    private static final int TYPE_ITEM = 10;
    private static final int TYPE_FOOTER = 11;
    public List<T> list;//数据集合
    private int itemLayout;//item的布局
    public Context context;
    private boolean isNeedMore;//是否需要加载更多的view

    public boolean isNeedMore() {
        return isNeedMore;
    }

    public void setIsNeedMore(boolean isNeedMore) {
        this.isNeedMore = isNeedMore;
    }

    /**
     * item的点击和长按监听
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public MyRecycleViewAdapter(List<T> list,int itemLayout,Context context,boolean isNeedMore){
        this.list = list;
        this.itemLayout = itemLayout;
        this.context = context;
        this.isNeedMore = isNeedMore;
    }

    public List<T> getList() {
        return list;
    }
    public void setList(List<T> list) {
        this.list = list;
    }
    public int getItemLayout() {
        return itemLayout;
    }
    public void setItemLayout(int itemLayout) {
        this.itemLayout = itemLayout;
    }

    @Override
    public int getItemViewType(int position) {
        if(!isNeedMore) {
            return super.getItemViewType(position);
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    //1、第一步，先在onCreateViewHolder里面添加item的布局，添加到RecyclerView里面
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if(viewType == TYPE_FOOTER) {
                return new FooterViewHolder(
                        LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.recycle_foot_view, parent, false));
            }else {
                View view = LayoutInflater.from(context).inflate(itemLayout, parent, false);
                return new MyViewHolder(view);
            }
    }


    //3、第三步，把ViewHolder传递到onBindViewHolder,进行item的数据绑定
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            //((MyViewHolder) holder).item.setText(position + "");
            if(TYPE_ITEM == getItemViewType(position)
                    || super.getItemViewType(position) == getItemViewType(position)) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setPositionClick((MyViewHolder) holder,position);
                    }
                });
                holder.itemView.setTag(position);
                initData((MyViewHolder) holder, position);
            }
//            holder.itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    onItemClickListener.onItemClick(holder.itemView, position);
//                }
//            });
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    int position = holder.getLayoutPosition();
//                    onItemClickListener.onItemLongClick(holder.itemView, position);
//                    return false;
//                }
//            });

    }

    @Override
    public int getItemCount() {
            if(isNeedMore) {
                return list.size()==0?0:list.size()+1;
            }else {
                return list.size();
            }
    }

    /**
     *itemview 的点击事件
     * @param position
     */
    protected abstract void setPositionClick(MyViewHolder holder,int position);

    /**
     * 对item进行加载数据
     * @param holder recycleview的ViewHolder position位置
     * @param position
     */
    protected abstract void initData(MyViewHolder holder, int position);

    //2、第二步，zaiViewHolder里面初始化视图
    public class MyViewHolder extends RecyclerView.ViewHolder {

        private View findView;
        private View convertView;
        private SparseArray<Object> tags;
        //用来替代Map<Integer,Object>的容器, 效率比map高
        private SparseArray<View> views;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.convertView = itemView;
            this.findView = itemView;
            tags = new SparseArray<>();
            views = new SparseArray<View>();
        }

        public View getConverView() {
            return convertView;
        }

        public View getFindView() {
            return findView;
        }
        //    /**
        //     * 根据视图id得到对应的视图对象
        //     * @param viewId
        //     * @return
        //     */
        //    public <T extends View> T getView(@IdRes int viewId) {
        //        View view = views.get(viewId);
        //        if (view == null) {
        //            view = convertView.findViewById(viewId);
        //            views.put(viewId, view);
        //        }
        //        return (T) view;
        //    }
                /**
                 * 根据 viewId 获取一个 View 对象
                 */
        //    public View getView(@IdRes int viewId) {
        //        View view;
        //        Object viewObj = findView.getTag(viewId);
        //        if (null != viewObj) {
        //            view = (View) viewObj;
        //        } else {
        //            view = findView.findViewById(viewId);
        //            findView.setTag(viewId, view);
        //        }
        //        return view;
        //    }
        public <T extends View> T  getView(@IdRes int viewId) {
            View view;
            Object viewObj = findView.getTag(viewId);
            if (null != viewObj) {
                view = (View) viewObj;
            } else {
                view = findView.findViewById(viewId);
                findView.setTag(viewId, view);
            }
            return (T) view;
        }

        /**
         * 设置指定的id视图的可见性
         * @param viewId
         * @param visibility
         * @return
         */
        public MyViewHolder setViewVisibility(@IdRes int viewId,int visibility){
            if(getView(viewId)!=null) {
                getView(viewId).setVisibility(visibility);
            }
            return this;
        }
        /**
         * 根据 viewId 获取一个 ImageView 对象
         */
        public ImageView getImageView(@IdRes int viewId) {
            return (ImageView) getView(viewId);
        }

        /**
         * 根据 viewId 获取一个 TextView 对象
         */
        public TextView getTextView(@IdRes int viewId) {
            return (TextView) getView(viewId);
        }

        /**
         * 根据 viewId 获取一个 CheckBox 对象
         */
        public CheckBox getCheckBox(@IdRes int viewId) {
            return (CheckBox) getView(viewId);
        }

        /**
         * 为指定 viewId 的 ImageView 对象设置图片
         */
        public MyViewHolder setImageDrawable(@IdRes int viewId, @Nullable Drawable drawable) {
            if (getImageView(viewId)!=null) {
                getImageView(viewId).setImageDrawable(drawable);
            }
            return this;
        }

        /**
         * 为指定 viewId 的 ImageView 对象设置图片
         */
        public MyViewHolder setImageResource(@IdRes int viewId, @DrawableRes int resId) {
            if (getImageView(viewId)!=null) {
                getImageView(viewId).setImageResource(resId);
            }
            return this;
        }
        /**
         * 为指定 viewId 的 ImageView 对象设置图片
         */
        public MyViewHolder setImageBitmap(@IdRes int viewId, Bitmap bitmap) {
            if (getImageView(viewId)!=null) {
                getImageView(viewId).setImageBitmap(bitmap);
            }
            return this;
        }

        /**
         * 未指定的viewId 的ImageView 对象设置背景图片
         * @param viewId
         * @param drawable
         * @return
         */
        public MyViewHolder setImageBackgroud(@IdRes int viewId, @Nullable Drawable drawable) {
            if (getImageView(viewId)!=null) {
                getImageView(viewId).setBackground(drawable);
            }
            return this;
        }
        /**
         * 使用 Gild 为指定 viewId 的 ImageView 对象设置图片
         */
        public MyViewHolder bindImage(@IdRes int viewId, @NonNull String url) {
            if (getImageView(viewId)!=null) {
                Glide.with(context)
                        .load("http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=false&word=刘诗诗&hs=0&pn=-1&spn=0&di=baikeimg&pi=&rn=1&tn=baiduimagedetail&is=&istype=&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=&lpn=0&ln=undefined&fr=&fmq=undefined&fm=undefined&ic=&s=&se=&sme=&tab=&width=&height=&face=&cg=star&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimgsrc.baidu.com%2Fbaike%2Fpic%2Fitem%2F5366d0160924ab185552ce8036fae6cd7a890bc4.jpg&fromurl=http%3A%2F%2Fbaike.baidu.com%2Fview%2F442490.htm&gsm=")
                        .into(getImageView(viewId));
            }
            return this;
        }


        /**
         * 为指定 viewId 的 TextView 对象设置文字
         */
        public MyViewHolder setText(@IdRes int viewId, @StringRes int resid) {
            if (getTextView(viewId)!=null) {
                getTextView(viewId).setText(resid);
            }
            return this;
        }

        /**
         * 为指定 viewId 的 TextView 对象设置文字
         */
        public MyViewHolder setText(@IdRes int viewId, String text) {
            if (getTextView(viewId)!=null) {
                getTextView(viewId).setText(text);
            }
            return this;
        }

        public MyViewHolder setTextSize(@IdRes int viewId,@Size int size){
            if(getTextView(viewId) != null){
                getTextView(viewId).setTextSize(size);
            }
            return this;
        }


        /**
         * 为指定 viewId 的 TextView 对象设置文字颜色
         */
        public MyViewHolder setTextColor(@IdRes int viewId, @ColorInt int color) {
            if (getTextView(viewId)!=null) {
                getTextView(viewId).setTextColor(color);
            }
            return this;
        }

        /**
         * 为指定 viewId 的 View 对象设置 TAG
         */
        public MyViewHolder setTag(@IdRes int viewId, final Object tag) {
            if (getView(viewId)!=null) {
                getView(viewId).setTag(tag);
            }
            return this;
        }

        /**
         * 为指定 viewId 的 View 对象设置背景图片
         */
        public MyViewHolder setBackgroundResource(@IdRes int viewId, @DrawableRes int resid) {
            if (getView(viewId)!=null) {
                getView(viewId).setBackgroundResource(resid);
            }
            return this;
        }

        /**
         * 使用当前 ViewHolder 记录一个 TAG
         */
        public MyViewHolder putTag(int key, final Object tag) {
            tags.put(key, tag);
            return this;
        }

        /**
         * 从当前 ViewHolder 中取出一个TAG
         */
        public Object getTag(int key) {
            return tags.get(key);
        }

        /**
         * 为指定 viewId 的 CheckBox 对象设置选中状态
         */
        public MyViewHolder setChecked(@IdRes int viewId, boolean checked) {
            if (getCheckBox(viewId)!=null) {
                getCheckBox(viewId).setChecked(checked);
            }
            return this;
        }

        /**
         * 切换指定 viewId 的 CheckBox 的选中状态
         */
        public MyViewHolder toggle(@IdRes int viewId) {
            if (getCheckBox(viewId)!=null) {
                getCheckBox(viewId).toggle();
            }
            return this;
        }

        /**
         * 为指定 viewId 的 View 对象设置点击监听
         */
        public MyViewHolder setOnClickListener(@IdRes int viewId, @Nullable View.OnClickListener listener) {
            if (getView(viewId)!=null) {
                getView(viewId).setOnClickListener(listener);
            }

            return this;
        }

        /**
         * 为指定 viewId 的 View 对象设置是否可见
         */
        public MyViewHolder setVisibile(@IdRes int viewId, boolean visible) {
            if (getView(viewId)!=null) {
                getView(viewId).setVisibility(visible ? View.VISIBLE : View.GONE);
            }
            return this;
        }

        /**
         * 为指定 viewId 的 View 对象设置是否可见
         */
        public MyViewHolder setVisibility(@IdRes int viewId, int visibility) {
            if (getView(viewId)!=null) {
                getView(viewId).setVisibility(visibility);
            }
            return this;
        }

        /**
         * 为指定 viewId 的 View 对象设置布局参数
         */
        public MyViewHolder setLayoutParams(@IdRes int viewId, RelativeLayout.LayoutParams params) {
            if (getView(viewId)!=null) {
                getView(viewId).setLayoutParams(params);
            }
            return this;
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
}