/*
 * Copyright 2016 Hippo Seven
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hippo.ehviewer;

import android.content.Context;
import android.os.Environment;
import android.support.annotation.Nullable;

import com.hippo.yorozuya.FileUtils;

import java.io.File;

public class AppConfig {

    private static final String APP_DIRNAME = "EhViewer";

    private static final String DOWNLOAD = "download";
    private static final String TEMP = "temp";
    private static final String IMAGE = "image";
    private static final String CRASH = "crash";
    private static final String PARSE_ERROR = "parse_error";

    private static Context sContext;

    public static void initialize(Context context) {
        sContext = context.getApplicationContext();
    }

    @Nullable
    public static File getExternalAppDir() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File dir = new File(Environment.getExternalStorageDirectory(), APP_DIRNAME);
            return FileUtils.ensureDirectory(dir) ? dir : null;
        }
        return null;
    }

    /**
     * mkdirs and get
     */
    @Nullable
    public static File getDirInExternalAppDir(String filename) {
        File appFolder = getExternalAppDir();
        if (appFolder != null) {
            File dir = new File(appFolder, filename);
            return FileUtils.ensureDirectory(dir) ? dir : null;
        }
        return null;
    }

    @Nullable
    public static File getFileInExternalAppDir(String filename) {
        File appFolder = getExternalAppDir();
        if (appFolder != null) {
            File file = new File(appFolder, filename);
            return FileUtils.ensureFile(file) ? file : null;
        }
        return null;
    }

    @Nullable
    public static File getDefaultDownloadDir() {
        return getDirInExternalAppDir(DOWNLOAD);
    }

    @Nullable
    public static File getExternalTempDir() {
        return getDirInExternalAppDir(TEMP);
    }

    @Nullable
    public static File getExternalImageDir() {
        return getDirInExternalAppDir(IMAGE);
    }

    @Nullable
    public static File getExternalCrashDir() {
        return getDirInExternalAppDir(CRASH);
    }

    @Nullable
    public static File getExternalParseErrorDir() {
        return getDirInExternalAppDir(PARSE_ERROR);
    }

    @Nullable
    public static File createExternalTempFile() {
        return FileUtils.createTempFile(getExternalTempDir(), null);
    }
}
