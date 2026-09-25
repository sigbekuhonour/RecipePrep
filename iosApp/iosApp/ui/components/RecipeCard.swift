import SwiftUI
import sharedLogic

struct RecipeCard: View {
    let recipe: RecipeSummary

    var body: some View {
        VStack(spacing: 12) {
            AsyncImage(url: URL(string: recipe.thumbnailUrl)) { image in
                image.resizable().aspectRatio(contentMode: .fill)
            } placeholder: {
                Color(.systemGray5)
            }
            .frame(width: 120, height: 80)
            .clipShape(RoundedRectangle(cornerRadius: 6))

            Text(recipe.title)
            .font(.recipeTitleMedium)
            .fontWeight(.bold)
            .lineLimit(2)
            .multilineTextAlignment(.leading)
            .frame(height: 40, alignment: .top)

            Spacer()
        }
        .padding(8)
        .clipShape(RoundedRectangle(cornerRadius: 12))
        .shadow(color: .black.opacity(0.1), radius: 5, x: 0, y: 2)
    }
}
