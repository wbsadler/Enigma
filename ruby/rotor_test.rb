#!/usr/bin/ruby
require "test/unit"
#require "rotor"

class RotorTest < Test::Unit::TestCase
  
  def test_simple()
    x=1
    assert(x==0,"x is not equal to 0, it's equal to " + x.to_s())
  end
  
  
end