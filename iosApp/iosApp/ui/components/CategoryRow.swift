import SwiftUI



import SwiftUI

struct CategoryChip: View {
    let category: String
    let isSelected: Bool
    let onTap: () -> Void

    var body: some View {
        Button(action: onTap) {
            Text(category)
                .font(.subheadline)
                .fontWeight(isSelected ? .bold : .regular)
                .padding(.horizontal, 16)
                .padding(.vertical, 8)
                .background(isSelected ? .recipePrimary : Color(.systemGray6))
                .foregroundColor(isSelected ? .white : .black)
                .cornerRadius(20)
        }
        .buttonStyle(.plain)
    }
}

struct CategoryRow: View {
    let selectedCategory: String
    let onCategorySelected: (String) -> Void
    private let categories = ["Seafood", "Chicken", "Beef", "Vegetarian", "Dessert"]

    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            LazyHStack(spacing: 8) {
                ForEach(categories, id: \.self) { category in
                    CategoryChip(
                        category: category,
                        isSelected: category == selectedCategory,
                        onTap: { onCategorySelected(category) }
                    )
                }
            }
        }
        .fixedSize(horizontal: false, vertical: true)}
}
