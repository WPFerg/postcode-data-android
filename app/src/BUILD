load("@gmaven_rules//:defs.bzl", "gmaven_artifact")

android_library(
    name = "main",
    srcs = glob(["wpferg/postcodes/android/**/*.java"]),
    custom_package = "wpferg.postcodes.android",
    manifest = "main/AndroidManifest.xml",
    resource_files = glob(["main/res/**"]),
    deps = [
    # NOTE: these don't resolve correctly
        gmaven_artifact("com.android.support:appcompat-v7:27.1.1"),
        gmaven_artifact("com.android.support.constraint:constraint-layout:1.1.3"),
        gmaven_artifact("com.android.support:support-v4:27.1.1"),
        gmaven_artifact("com.android.support:recyclerview-v7:27.1.1")
    ]
)

android_binary(
    name = "binary",
    srcs = glob(["wpferg/postcodes/android/**/*.java"]),
    manifest = "main/AndroidManifest.xml",
    resource_files = glob(["main/res/**"]),
    deps = [":main"]
)

