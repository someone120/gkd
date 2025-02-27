package li.songe.selector.data

sealed class CompareOperator(val key: String) {
    override fun toString() = key
    abstract fun compare(left: Any?, right: Any?): Boolean

    companion object {
        val allSubClasses = listOf(
            Equal,
            NotEqual,
            Start,
            NotStart,
            Include,
            NotInclude,
            End,
            NotEnd,
            Less,
            LessEqual,
            More,
            MoreEqual
        ).sortedBy { -it.key.length }
    }

    object Equal : CompareOperator("=") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is CharSequence && right is CharSequence) left.contentEquals(right) else left == right
        }
    }

    object NotEqual : CompareOperator("!=") {
        override fun compare(left: Any?, right: Any?) = !Equal.compare(left, right)
    }

    object Start : CompareOperator("^=") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is CharSequence && right is CharSequence) left.startsWith(right) else false
        }
    }

    object NotStart : CompareOperator("!^=") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is CharSequence && right is CharSequence) !left.startsWith(right) else false
        }
    }

    object Include : CompareOperator("*=") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is CharSequence && right is CharSequence) left.contains(right) else false
        }
    }

    object NotInclude : CompareOperator("!*=") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is CharSequence && right is CharSequence) !left.contains(right) else false
        }
    }

    object End : CompareOperator("$=") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is CharSequence && right is CharSequence) left.endsWith(right) else false
        }
    }

    object NotEnd : CompareOperator("!$=") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is CharSequence && right is CharSequence) !left.endsWith(right) else false
        }
    }

    object Less : CompareOperator("<") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is Int && right is Int) left < right else false
        }
    }

    object LessEqual : CompareOperator("<=") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is Int && right is Int) left <= right else false
        }
    }

    object More : CompareOperator(">") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is Int && right is Int) left > right else false
        }
    }

    object MoreEqual : CompareOperator(">=") {
        override fun compare(left: Any?, right: Any?): Boolean {
            return if (left is Int && right is Int) left >= right else false
        }
    }

}