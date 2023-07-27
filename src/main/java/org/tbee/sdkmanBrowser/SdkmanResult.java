package org.tbee.sdkmanBrowser;

public record SdkmanResult(SdkmanPackage[] result) {
    /*
        {
            "result": [
                {
                    "id": "a8bb740966ccfcab9a5f04f818e93e6f",
                    "archive_type": "zip",
                    "distribution": "liberica",
                    "major_version": 20,
                    "java_version": "20+37",
                    "distribution_version": "20",
                    "jdk_version": 20,
                    "latest_build_available": false,
                    "release_status": "ga",
                    "term_of_support": "sts",
                    "operating_system": "macos",
                    "lib_c_type": "libc",
                    "architecture": "amd64",
                    "fpu": "unknown",
                    "package_type": "jre",
                    "javafx_bundled": false,
                    "directly_downloadable": true,
                    "filename": "bellsoft-jre20+37-macos-amd64.zip",
                    "links": {
                        "pkg_info_uri": "https://api.foojay.io/disco/v3.0/ids/a8bb740966ccfcab9a5f04f818e93e6f",
                        "pkg_download_redirect": "https://api.foojay.io/disco/v3.0/ids/a8bb740966ccfcab9a5f04f818e93e6f/redirect"
                    },
                    "free_use_in_production": true,
                    "tck_tested": "yes",
                    "tck_cert_uri": "https://bell-sw.com/libericajdk/",
                    "aqavit_certified": "unknown",
                    "aqavit_cert_uri": "",
                    "size": 58573504,
                    "feature": []
                }
            ]
        }
     */
}
