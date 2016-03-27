var gulp = require('gulp'),
    jade = require('gulp-jade');
gulp.task('jade',function() {
	gulp.src('cms/**/**.jade')
	   .pipe(jade())
	   .pipe(gulp.dest('b'));
});