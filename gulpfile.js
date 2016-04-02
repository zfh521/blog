var gulp = require('gulp'),
    watch = require('gulp-watch'),
    jade = require('gulp-jade'),
    less = require('gulp-less'),
    del = require('del'),
    sourcemaps = require('gulp-sourcemaps'),
    connect = require('gulp-connect');
gulp.task('connect', function() {
  connect.server({
    root: './',
    livereload: true
  });
});
gulp.task('clean',function(cb){
   del(['vendor'],cb)
});
function buildLess(event){
  console.log(JSON.stringify(event));
  gulp.src('app/less/app.less')
      .pipe(sourcemaps.init())
      .pipe(less())
      .pipe(sourcemaps.write({includeContent: false, sourceRoot: '/app'}))
      .pipe(gulp.dest('public/'))
}
function buildJade(event){
  console.log('File ' + event.path + ' was ' + event.event + ', running tasks...');
  gulp.src('cms/**/**.jade')
     .pipe(jade({pretty: true}))
     .pipe(gulp.dest('b'));
}
gulp.task('less',buildLess);
gulp.task('jade',buildJade);

gulp.task('watch',function(){
	watch('cms/**/**.jade',buildJade);
	watch('app/**/**.less',buildLess);
});
gulp.task('default', ['connect', 'watch']);