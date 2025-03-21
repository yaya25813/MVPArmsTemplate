package other.src.app_package

import other.ArmsPluginTemplateProviderImpl
import other.armsAnnotation

fun armsComponent(isKt: Boolean, provider: ArmsPluginTemplateProviderImpl) =
    if (isKt) armsComponentKt(provider) else armsComponentJava(provider)

private fun armsComponentKt(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.componentPackageName.value}
import dagger.Component
import com.jess.arms.di.component.AppComponent
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module

${
    if (provider.needActivity.value && provider.needFragment.value)
        """
import com.jess.arms.di.scope.ActivityScope
import ${provider.activityPackageName.value}.${provider.pageName.value}Activity
import ${provider.fragmentPackageName.value}.${provider.pageName.value}Fragment
"""
    else if (provider.needActivity.value)
        """
import com.jess.arms.di.scope.ActivityScope
import ${provider.activityPackageName.value}.${provider.pageName.value}Activity
"""
    else if (provider.needFragment.value)
        """
import com.jess.arms.di.scope.FragmentScope
import ${provider.fragmentPackageName.value}.${provider.pageName.value}Fragment
"""
    else ""
}

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
@Component(modules = [${provider.pageName.value}Module::class], dependencies = [AppComponent::class])
interface ${provider.pageName.value}Component {
${
    if (provider.needActivity.value && provider.needFragment.value) {
        """
    fun inject(activity:${provider.pageName.value}Activity)
    fun inject(fragment:${provider.pageName.value}Fragment)
    """
    } else if (provider.needActivity.value) {
        """
    fun inject(activity:${provider.pageName.value}Activity)
    """
    } else if (provider.needFragment.value) {
        """
    fun inject(fragment:${provider.pageName.value}Fragment)
    """
    } else ""
}
}
    
"""


fun armsComponentJava(provider: ArmsPluginTemplateProviderImpl) = """
package ${provider.componentPackageName.value};
import com.jess.arms.di.component.AppComponent;

import dagger.BindsInstance; 
import dagger.Component;
import ${provider.moudlePackageName.value}.${provider.pageName.value}Module;
import ${provider.contractPackageName.value}.${provider.pageName.value}Contract;

${
    if (provider.needActivity.value && provider.needFragment.value)
        """
import com.jess.arms.di.scope.ActivityScope;
import ${provider.activityPackageName.value}.${provider.pageName.value}Activity;
import ${provider.fragmentPackageName.value}.${provider.pageName.value}Fragment;
"""
    else if (provider.needActivity.value)
        """
import com.jess.arms.di.scope.ActivityScope;
import ${provider.activityPackageName.value}.${provider.pageName.value}Activity;
"""
    else if (provider.needFragment.value)
        """
import com.jess.arms.di.scope.FragmentScope;
import ${provider.fragmentPackageName.value}.${provider.pageName.value}Fragment;
"""
    else ""
}

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
@Component(modules = ${provider.pageName.value}Module.class,dependencies = AppComponent.class)
public interface ${provider.pageName.value}Component {
${
    if (provider.needActivity.value && provider.needFragment.value) {
        """
    void inject(${provider.pageName.value}Activity activity);
    void inject(${provider.pageName.value}Fragment fragment);
     @Component.Builder
    interface Builder {
        @BindsInstance
        ${provider.pageName.value}Component.Builder view(${provider.pageName.value}Contract.View view);

        ${provider.pageName.value}Component.Builder appComponent(AppComponent appComponent);

        ${provider.pageName.value}Component build();
    }
    """
    } else if (provider.needActivity.value) {
        """
    void inject(${provider.pageName.value}Activity activity);
     @Component.Builder
    interface Builder {
        @BindsInstance
        ${provider.pageName.value}Component.Builder view(${provider.pageName.value}Contract.View view);

        ${provider.pageName.value}Component.Builder appComponent(AppComponent appComponent);

        ${provider.pageName.value}Component build();
    }
    """
    } else if (provider.needFragment.value) {
        """
    void inject(${provider.pageName.value}Fragment fragment);
     @Component.Builder
    interface Builder {
        @BindsInstance
        ${provider.pageName.value}Component.Builder view(${provider.pageName.value}Contract.View view);

        ${provider.pageName.value}Component.Builder appComponent(AppComponent appComponent);
    
        ${provider.pageName.value}Component build();
    }
    """
    } else ""
}
}
"""