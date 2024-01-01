import SwiftUI
import shared

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        Main_iosKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
	let greet = Greeting().greet()

	var body: some View {
		//Text(greet)
		ComposeView()
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
		//App()
	}
}

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