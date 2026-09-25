import SwiftUI
import sharedLogic

struct RecipeDetailScreen: View {
    let recipeId: String
    let viewModel: RecipeDetailViewModel
    @State private var state: any RecipeDetailUiState
    
    init(recipeId: String, viewModel: RecipeDetailViewModel? = nil) {
        self.recipeId = recipeId
        self.viewModel = viewModel ?? SwiftInjectionHelper.shared.createRecipeDetailViewModel(recipeId: recipeId)
        _state = State(initialValue: self.viewModel.uiState.value)
    }
    
    var body: some View {
        ScrollView{
            switch onEnum(of: state) {
            case .loading:
                ProgressView()
                    .frame(maxWidth: .infinity)
                    .padding(.top, 40)
                
            case .content(let content):
                VStack(alignment: .leading, spacing: 8){
                    AsyncImage(url: URL(string: content.recipe.thumbnailUrl)) { image in
                        image.resizable().aspectRatio(contentMode: .fill)
                    } placeholder: {
                        Color(.systemGray5)
                    }
                    .frame(maxWidth: .infinity, minHeight: 200, maxHeight: 200)
                    .clipped()
                    
                    Text(content.recipe.title)
                    .padding(.leading,10)
                    
                    HStack(spacing:3){
                        Text(content.recipe.categoryValue)
                        Text("•")
                        Text(content.recipe.area)
                    }
                    .font(.recipeLabel)
                    .foregroundStyle(.secondary)
                    .padding(.leading,10)
                    
                    Spacer()
                    
                    Text("Ingredients")
                    .font(.recipeHeadlineLarge)
                    .padding(.leading,10)
                    
                    ForEach(content.recipe.ingredients,  id: \.self){ ingredient in
                        HStack{
                            Text("•")
                            .padding(.leading,10)
                            Text(ingredient.name)
                            Spacer()
                            Text(ingredient.measure)
                            .padding(.trailing,10)
                            .foregroundStyle(.secondary)
                        }
                    }
                }
            case .error(let error):
                Text(error.message)
                    .foregroundStyle(.secondary)
                    .padding()
            }
        }
        .frame(maxWidth: .infinity, alignment: .leading)
        .navigationBarBackButtonHidden(false)
        .task {
            for await uiState in viewModel.uiState {
                state = uiState
            }
        }
        .onDisappear {
            SwiftInjectionHelper.shared.dispose(viewModel: viewModel)
        }
    }
}
