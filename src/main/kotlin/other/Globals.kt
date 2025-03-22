package other

import java.text.SimpleDateFormat
import java.util.*

fun commonAnnotation(provider: ArmsPluginTemplateProviderImpl) = """
/**
 * Created on： ${SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date(System.currentTimeMillis()))}
 * @author rwll 
 * 描述 : ${provider.pageName.value}${if (provider.needActivity.value) "Activity" else "Fragment"}
 */
""".trimIndent()

val armsAnnotation = """
/**
 * Created on： ${SimpleDateFormat("yyyy/MM/dd HH:mm").format(Date(System.currentTimeMillis()))}
 * @author rwll 
 */
""".trimIndent()