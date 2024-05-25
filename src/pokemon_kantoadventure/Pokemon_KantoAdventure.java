// /*
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
//  * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
//  */
// package pokemon_kantoadventure;

// import Trainer.Trainer;
// import PokemonBattle_LevelUp.*;
// import pokemons.*;
// import java.lang.reflect.Constructor;
// import java.lang.reflect.Method;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.ArrayList;
// import java.util.List;

// public class Pokemon_KantoAdventure {

//     // public static void main(String[] args) {
//     // // Create a trainer
//     // Trainer trainer = new Trainer();

//     // // Set the trainer's current location
//     // Location palletTown = new Location(Location.VIRIDIAN_CITY);
//     // trainer.setCurrentLocation(palletTown);

//     // // Instantiate Location
//     // Location location = new Location(trainer.getCurrentLocation().getName());
//     // location.loadPokemons(trainer);

//     // // Get wild Pokemons
//     // List<Pokemon> wildPokemons = location.getWildPokemons();
//     // System.out.println("Wild Pokemons in " + location.getName() + ":");
//     // for (Pokemon p : wildPokemons) {
//     // System.out.println(p.getName());
//     // }

//     // // Get gym leader Pokemons
//     // List<Pokemon> gymLeaderPokemons = location.getGymLeaderPokemons();
//     // System.out.println("Gym Leader Pokemons in " + location.getName() + ":");
//     // for (Pokemon p : gymLeaderPokemons) {
//     // System.out.println(p.getName());
//     // }
//     }


//     // public static void main(String[] args) {
//         // List<Class<?>> subclasses = new ArrayList<>();
//         // // Assuming Pokemon is the superclass
//         // Class<?> superclass = Pokemon.class; // Replace Pokemon with your superclass name

//         // // Get all classes in the same package as the superclass
//         // Package pkg = superclass.getPackage();
//         // String packageName = pkg.getName();
//         // for (Class<?> c : getClasses(packageName)) {
//         //     if (superclass.isAssignableFrom(c) && !superclass.equals(c)) {
//         //         subclasses.add(c);
//         //     }
//         // }

//         // Access the constructors of each subclass
//         // for (Class<?> subclass : subclasses) {
//         //     try {
//         //         System.out.println("Location of " + subclass.getSimpleName() + ":");
//         //         Constructor<?> constructor = subclass.getConstructor();
//         //         Object instance = constructor.newInstance();
//         //         Method getLocationMethod = subclass.getMethod("getLocation");
//         //         Object location = getLocationMethod.invoke(instance);
//         //         System.out.println(location);
//         //     } catch (Exception e) {
//         //         e.printStackTrace();
//         //     }
//         // }
//     //     for (Class<?> subclass : subclasses) {
//     //         try {
//     //             System.out.println("Location of " + subclass.getSimpleName() + ":");
//     //             Pokemon pokemon = (Pokemon) subclass.getDeclaredConstructor().newInstance();
//     //             System.out.println(pokemon.getLocation());
//     //         } catch (Exception e) {
//     //             e.printStackTrace();
//     //         }
//     //     }
//     // }

//     // // Helper method to get all classes in a package
//     // public static List<Class<?>> getClasses(String packageName) {
//     //     List<Class<?>> classes = new ArrayList<>();
//     //     try {
//     //         ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//     //         String path = packageName.replace('.', '/');
//     //         java.net.URL resource = classLoader.getResource(path);
//     //         java.nio.file.Path directory = java.nio.file.Paths.get(resource.toURI());
//     //         java.util.stream.Stream<java.nio.file.Path> files = java.nio.file.Files.walk(directory);
//     //         files.forEach(file -> {
//     //             if (file.toString().endsWith(".class")) {
//     //                 String className = file.toString()
//     //                         .replace(directory.toString(), "")
//     //                         .replace(".class", "")
//     //                         .replace('/', '.')
//     //                         .replace('\\', '.')
//     //                         .substring(1); // Remove leading dot
//     //                 try {
//     //                     classes.add(Class.forName(packageName + "." + className));
//     //                 } catch (ClassNotFoundException e) {
//     //                     // Handle exception
//     //                 }
//     //             }
//     //         });
//     //         files.close();
//     //     } catch (Exception e) {
//     //         // Handle exception
//     //     }
//     //     return classes;
//     // }
// }
