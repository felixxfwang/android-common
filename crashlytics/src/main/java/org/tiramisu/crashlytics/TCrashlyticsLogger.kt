package org.tiramisu.crashlytics

import com.google.auto.service.AutoService
import org.tiramisu.log.ILogger

/**
 * @author felixxfwang
 */
@AutoService(ILogger::class)
class TCrashlyticsLogger: ILogger {
    override fun v(tag: String, message: String) {
        TCrashlytics.log(tag, message)
    }

    override fun i(tag: String, message: String) {
        TCrashlytics.log(tag, message)
    }

    override fun d(tag: String, message: String) {
        TCrashlytics.log(tag, message)
    }

    override fun w(tag: String, message: String) {
        TCrashlytics.log(tag, message)
    }

    override fun e(tag: String, message: String, tr: Throwable?) {
        TCrashlytics.log(tag, message)
    }
}