package arrays

class Kotlin {

  fun kthSmallest(mat: Array<IntArray>, k: Int): Int =
      mat.drop(1)
          .fold(mat.first().toList()) { best, row ->
            best.flatMap { x -> row.map { y -> x + y } }
                .sorted()
                .take(k)
          }[k - 1]

}
