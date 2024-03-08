package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation

fun armsPresenter(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl): String =
    if (isKt) armsPresenterKt(provider) else armsPresenterJava(provider)

private fun armsPresenterKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.presenterPackageName.value}
import android.app.Application
import com.jess.arms.integration.AppManager
${
    if (provider.needActivity.value && provider.needFragment.value)
        "import com.jess.arms.di.scope.ActivityScope"
    else if (provider.needActivity.value)
        "import com.jess.arms.di.scope.ActivityScope"
    else if (provider.needFragment.value)
        "import com.jess.arms.di.scope.FragmentScope"
    else ""
}
import com.jess.arms.mvp.BasePresenter
import com.jess.arms.http.imageloader.ImageLoader
import me.jessyan.rxerrorhandler.core.RxErrorHandler
import javax.inject.Inject
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract

$armsAnnotation
${
    if (provider.needActivity.value && provider.needFragment.value)
        "@ActivityScope"
    else if (provider.needActivity.value)
        "@ActivityScope"
    else if (provider.needFragment.value)
        "@FragmentScope"
    else ""
}
class ${provider.pageName.value}Presenter
@Inject
constructor(model: ${provider.pageName.value}Contract.Model, rootView: ${provider.pageName.value}Contract.View) :
BasePresenter<${provider.pageName.value}Contract.Model, ${provider.pageName.value}Contract.View>(model,rootView) {
    @Inject
    lateinit var mErrorHandler: RxErrorHandler
    @Inject
    lateinit var mApplication: Application
    @Inject
    lateinit var mImageLoader: ImageLoader
    @Inject
    lateinit var mAppManager: AppManager
    override fun onDestroy() {
          super.onDestroy()
    }
    
//    fun squareTopicHotList(objectMap: HashMap<String, Any?>?) {
//        mModel!!.squareTopicHotList(objectMap)
//            .compose(RxUtils.applySchedulers(mRootView))
//            ?.subscribe(object :
//                ErrorHandleSubscriber<BaseResponse<PaginationEntity<List<SquareTopicMainEntity>>>>(
//                    mErrorHandler
//                ) {
//                override fun onNext(userBeanBaseResponse: BaseResponse<PaginationEntity<List<SquareTopicMainEntity>>>) {
//                    if (userBeanBaseResponse.isSuccess) {
//                        mRootView.squareTopicHotList(userBeanBaseResponse.data)
//                    }
//                }
//            })
//    }
}   
"""


fun armsPresenterJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.presenterPackageName.value};
import android.app.Application;
import com.jess.arms.integration.AppManager;
${
    if (provider.needActivity.value && provider.needFragment.value)
        "import com.jess.arms.di.scope.ActivityScope;"
    else if (provider.needActivity.value)
        "import com.jess.arms.di.scope.ActivityScope;"
    else if (provider.needFragment.value)
        "import com.jess.arms.di.scope.FragmentScope;"
    else ""
}
import com.jess.arms.mvp.BasePresenter;
import com.jess.arms.http.imageloader.ImageLoader;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import javax.inject.Inject;
import java.util.Map;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;

$armsAnnotation
${
    if (provider.needActivity.value && provider.needFragment.value)
        "@ActivityScope"
    else if (provider.needActivity.value)
        "@ActivityScope"
    else if (provider.needFragment.value)
        "@FragmentScope"
    else ""
}
public class ${provider.pageName.value}Presenter extends BasePresenter<${provider.pageName.value}Contract.Model, ${provider.pageName.value}Contract.View>{
    @Inject
    RxErrorHandler mErrorHandler;
    @Inject
    Application mApplication;
    @Inject
    ImageLoader mImageLoader;
    @Inject
    AppManager mAppManager;
    
    @Inject
    public ${provider.pageName.value}Presenter (${provider.pageName.value}Contract.Model model, ${provider.pageName.value}Contract.View rootView) {
        super(model, rootView);
    }
    
//    public void accountDetail(Map<String, Object> map) {
//        mModel.accountDetail(map)
//                .compose(RxUtils.applySchedulers(mRootView))
//                .subscribe(new ErrorHandleSubscriber<BaseResponse<PaginationEntity<List<SecretCoinEntity>>>>(mErrorHandler) {
//                    @Override
//                    public void onNext(BaseResponse<PaginationEntity<List<SecretCoinEntity>>> userBeanBaseResponse) {
//                        if (userBeanBaseResponse.getSuccess()) {
//                            if (DataTool.isNotEmpty(userBeanBaseResponse.getData())) {
//                                mRootView.accountDetail(userBeanBaseResponse.getData().getTotal(), userBeanBaseResponse.getData().getRows());
//                            }
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable t) {
//                        super.onError(t);
//                        Timber.e("发生意外 ： " + t.toString());
//                    }
//                });
//    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }
}   
"""
