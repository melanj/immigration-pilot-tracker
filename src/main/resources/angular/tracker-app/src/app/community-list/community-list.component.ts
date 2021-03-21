import {Component, OnInit} from '@angular/core';
import {CommunityService} from "../community.service";
import {Community} from "../community";

@Component({
  selector: 'app-community-list',
  templateUrl: './community-list.component.html',
  styleUrls: ['./community-list.component.scss']
})
export class CommunityListComponent implements OnInit {
  communities: Community[];

  constructor(private communityService: CommunityService) {
  }

  ngOnInit(): void {
    this.communityService.getCommunities()
      .subscribe(communities => this.communities = communities);
  }

}
