package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation
import other.commonAnnotation

fun armsAdapter(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) = if (isKt) armsAdapterKt(provider) else armsAdapterJava(provider)

private fun armsAdapterKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.adapterPackageName.value}
import android.content.Context
import android.view.ViewGroup  
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.QuickViewHolder
import ${provider.appPackageName.value}.R 
import  ${provider.entityPackageName.value}.${provider.pageName.value}Entity;
class ${provider.pageName.value}Adapter() :
    BaseQuickAdapter<${provider.pageName.value}Entity?, QuickViewHolder>() {
    override fun onCreateViewHolder(
        context: Context,
        viewGroup: ViewGroup,
        i: Int
    ): QuickViewHolder {
        return QuickViewHolder(R.layout.${provider.adapterLayoutName.value}, viewGroup)
    }

    override fun onBindViewHolder(helper: QuickViewHolder, i1: Int, item: ${provider.pageName.value}Entity?) {
//       GlideImageLoaderStrategy().loadImage(
//            context,
//            ImageConfigImpl.builder()
//                .errorPic(R.mipmap.ic_launcher_round) //异常照片
//                .fallback(R.mipmap.ic_launcher_round) //异常照片
//                .url(item.getIcon()) // 图片地址
//                .imageView(helper.getView<ImageView>(R.id.iv_message_avatar)).build()
    }
}

"""

fun armsAdapterJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.adapterPackageName.value};
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.QuickViewHolder;
import android.content.Context;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import ${provider.appPackageName.value}.R;
import  ${provider.entityPackageName.value}.${provider.pageName.value}Entity;
public class ${provider.pageName.value}Adapter extends BaseQuickAdapter <${provider.pageName.value}Entity, QuickViewHolder> {

   @NonNull
    @Override
    protected QuickViewHolder onCreateViewHolder(@NonNull Context context, @NonNull ViewGroup viewGroup, int i) {
        return new QuickViewHolder(R.layout.${provider.adapterLayoutName.value}, viewGroup);
    }
    
   @Override
    protected void onBindViewHolder(@NonNull QuickViewHolder helper, int i, @Nullable ${provider.pageName.value}Entity item) {
//   new GlideImageLoaderStrategy().loadImage(getContext(),
//                    ImageConfigImpl.builder()
//                            .errorPic(R.mipmap.icon_parking_mobile_img)  //异常照片
//                            .imageRadius(8)  //图片圆角 isCircle(true) 圆图
//                            .url(item.getImage()) // 图片地址
//                            .imageView(helper.getView(R.id.iv_parking_mobile_item_img)).build());
    }
}   
"""
