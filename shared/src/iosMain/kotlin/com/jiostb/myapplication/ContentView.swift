import SwiftUI


struct ContentView1: View {
 var body: some View {

  NavigationStack {
   VStack {
    NavigationLink("This is screen number 1") {
     Text("Go to screen 1")
    }
    Spacer().frame(height: 10)
    NavigationLink("This is screen number 2") {
     Text("Go to screen 2")
    }
   }
  }
 }
}