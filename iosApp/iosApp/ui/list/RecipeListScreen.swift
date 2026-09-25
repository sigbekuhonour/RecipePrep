import SwiftUI
import sharedLogic

struct RecipeListScreen: View {
    @State private var mealName: String = ""
    @State private var state: any RecipeListUiState
    @State private var selectedCategory: String = "Seafood"
    let viewModel: RecipeListViewModel

    init(viewModel: RecipeListViewModel = SwiftInjectionHelper.shared.createRecipeListViewModel()) {
        self.viewModel = viewModel
        _state = State(initialValue: viewModel.uiState.value)
    }

    var body: some View {
        ScrollView {
            VStack(alignment: .leading) {
                TextField("Search meals...", text: $mealName)
                .textFieldStyle(.roundedBorder)
                .padding(.horizontal)
                .onChange(of: mealName) { oldValue, newValue in
                        viewModel.onSearchQueryChanged(query: newValue)
                    }
                
                CategoryRow(
                    selectedCategory: selectedCategory,
                    onCategorySelected: { category in
                        selectedCategory = category
                        viewModel.loadCategory(category: category)
                    }
                )
                .padding(.horizontal)
                .padding(.vertical, 8)
                
                switch onEnum(of: state) {
                   case .loading:
                       ProgressView()
                           .frame(maxWidth: .infinity)
                           .padding(.top, 40)

                   case .content(let content):
                    if content.recipes.isEmpty {
                           Text("No recipe found")
                               .foregroundStyle(.secondary)
                               .frame(maxWidth: .infinity)
                               .padding(.top, 40)
                    } else {
                        LazyVGrid(columns: [GridItem(.flexible()), GridItem(.flexible())], spacing: 12) {
                            ForEach(content.recipes, id: \.title) { recipe in
                                NavigationLink(value: recipe.idValue) {
                                    RecipeCard(recipe: recipe)
                                }
                                .buttonStyle(.plain)
                            }
                        }
                        .padding(.horizontal)
                    }
                    
                   case .error(let error):
                       Text(error.message)
                           .foregroundStyle(.secondary)
                           .padding()
                   }
               }
            }
        .frame(maxWidth: .infinity, alignment: .leading)
        .navigationTitle("Recipe Prep")
        .navigationDestination(for: String.self) { recipeId in
            RecipeDetailScreen(recipeId: recipeId)
        }
        .task {
            for await uiState in viewModel.uiState {
                state = uiState
            }
        }
        .onDisappear {
            SwiftInjectionHelper.shared.disposeRecipeListViewModel(viewModel: viewModel)
        }
    }
}

enum PreviewMocks {
    static var recipeListViewModel: RecipeListViewModel {
        SwiftInjectionHelper.shared.createPreviewRecipeListViewModel()
    }
}
#Preview {
    NavigationStack {
        RecipeListScreen(viewModel: PreviewMocks.recipeListViewModel)
    }
}
