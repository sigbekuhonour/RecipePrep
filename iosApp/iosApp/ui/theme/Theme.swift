//
// Created by Honour Sigbeku on 2026-08-16.
//

import SwiftUI

// MARK: - Color Tokens
extension Color {
    static let recipePrimary = Color(hex: 0xE65100)
    static let recipePrimaryContainer = Color(hex: 0xFFDBCF)

    static let recipeSecondary = Color(hex: 0x43664E)
    static let recipeSecondaryContainer = Color(hex: 0xC5ECCF)

    static let recipeBackground = Color(UIColor { trait in
        trait.userInterfaceStyle == .dark ? UIColor(hex: 0x181210) : UIColor(hex: 0xFCF8F6)
    })

    static let recipeSurface = Color(UIColor { trait in
        trait.userInterfaceStyle == .dark ? UIColor(hex: 0x201A18) : UIColor(hex: 0xFFFFFF)
    })

    static let recipeSurfaceVariant = Color(UIColor { trait in
        trait.userInterfaceStyle == .dark ? UIColor(hex: 0x53433C) : UIColor(hex: 0xF4DED4)
    })
}

// MARK: - Typography Tokens
extension Font {
        static let recipeHeadlineLarge = Font.custom("Montserrat-Bold", size: 28)
        static let recipeHeadlineMedium = Font.custom("Montserrat-SemiBold", size: 22)
        static let recipeTitleLarge = Font.custom("Montserrat-Bold", size: 18)
        static let recipeTitleMedium = Font.custom("Montserrat-SemiBold", size: 15)
        static let recipeBodyLarge = Font.custom("Montserrat-Regular", size: 15)
        static let recipeBodyMedium = Font.custom("Montserrat-Regular", size: 13)
        static let recipeLabel = Font.custom("Montserrat-Medium", size: 12)
}

// MARK: - Hex Color Helper Initializers
extension Color {
    init(hex: UInt, alpha: Double = 1.0) {
        self.init(
            .sRGB,
            red: Double((hex >> 16) & 0xff) / 255,
            green: Double((hex >> 08) & 0xff) / 255,
            blue: Double((hex >> 00) & 0xff) / 255,
            opacity: alpha
        )
    }
}

extension UIColor {
    convenience init(hex: UInt, alpha: CGFloat = 1.0) {
        self.init(
            red: CGFloat((hex >> 16) & 0xff) / 255.0,
            green: CGFloat((hex >> 08) & 0xff) / 255.0,
            blue: CGFloat((hex >> 00) & 0xff) / 255.0,
            alpha: alpha
        )
    }
}
