package com.achtien.myapplication.scan

import com.juul.kable.Scanner
import com.juul.kable.logs.Logging

class BrightAIScanner {
    companion object {
        val scanner by lazy {
            Scanner {
                logging {
                    level = Logging.Level.Events
                }
            }
        }
    }
}
