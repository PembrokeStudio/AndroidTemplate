package pembroke.studio.template.dagger

import javax.inject.Qualifier


object Qualifiers {
    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class TokenPreference

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class UserPreference
}
